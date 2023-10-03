package dev.vivek.productservicetutorial.services;

import dev.vivek.productservicetutorial.clients.fakestoreapi.FakeStoreClient;
import dev.vivek.productservicetutorial.clients.fakestoreapi.FakeStoreProductDto;
import dev.vivek.productservicetutorial.dtos.ProductDto;
import dev.vivek.productservicetutorial.models.Category;
import dev.vivek.productservicetutorial.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreProductServiceImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
//        RestTemplate restTemplate = restTemplateBuilder.build();
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    /*@Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<List> l = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                List.class
        );
        List<Product> answer = new ArrayList<>();
        for(Object object: l.getBody()){
            HashMap<String,Object> hm = (HashMap<String, Object>) object;


            Product product = new Product();
            product.setId(Long.valueOf((Integer)hm.get("id")));
            product.setTitle((String)hm.get("title"));
            product.setPrice(Double.valueOf(hm.get("price").toString()));
            Category category = new Category();
            category.setName((String)hm.get("category"));
            product.setCategory(category);
            product.setImageUrl((String)hm.get("image"));
            product.setDescription((String)hm.get("description"));
            //product.setRating(()hm.get("rating"));
            answer.add(product);
        }
        return answer;
    }*/
    @Override
    public List<Product> getAllProducts() {
        /*RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto[]> l = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );*/
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getAllProducts();

        List<Product> answer = new ArrayList<>();
        for(FakeStoreProductDto productDto: fakeStoreProductDtos){

            answer.add(convertFakeStoreProductDtoToProduct(productDto));
        }
        return answer;
    }
    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        product.setRating(productDto.getRating());
        return product;
    }
    @Override
    public Optional<Product> getSingleProduct(Long productId) {
        Optional<FakeStoreProductDto> fakeStoreProductDto= fakeStoreClient.getSingleProduct(productId);

        if (fakeStoreProductDto.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(convertFakeStoreProductDtoToProduct(fakeStoreProductDto.get()));
    }

    @Override
    public Product addNewProduct(ProductDto product) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.addNewProduct(product);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //restTemplate.pa
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity= requestForEntity(
                HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class,
                productId
        );
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDtoResponseEntity.getBody());
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {

        RestTemplate restTemplate = restTemplateBuilder.build();

        return null;
    }


    @Override
    public boolean deleteProduct(Long productId) {
        return false;
    }
}
