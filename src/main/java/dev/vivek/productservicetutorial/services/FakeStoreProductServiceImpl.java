package dev.vivek.productservicetutorial.services;

import dev.vivek.productservicetutorial.clients.fakestoreapi.FakeStoreClient;
import dev.vivek.productservicetutorial.clients.fakestoreapi.FakeStoreProductDto;
import dev.vivek.productservicetutorial.dtos.ProductDto;
import dev.vivek.productservicetutorial.models.Category;
import dev.vivek.productservicetutorial.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class FakeStoreProductServiceImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;
    private RedisTemplate<Long, Object> redisTemplate;
    private Map<Long, Object> fakeStoreProducts = new HashMap<>();
    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient, RedisTemplate<Long, Object> redisTemplate){
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
        this.redisTemplate = redisTemplate;
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

            answer.add(ProductConverter.convertFakeStoreProductDtoToProduct(productDto));
        }
        return answer;
    }
    /*private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto){
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
    }*/
    /*private FakeStoreProductDto convertProductToFakeStoreProductDto(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setRating(product.getRating());
        return fakeStoreProductDto;
    }*/
    @Override
    public Optional<Product> getSingleProduct(Long productId) {
//        if(redisTemplate.opsForHash().get())
        FakeStoreProductDto fakeStoreProductDto1 = (FakeStoreProductDto) redisTemplate.opsForHash().get(productId, "PRODUCTS");

        if(fakeStoreProductDto1!=null){
            return Optional.of(ProductConverter.convertFakeStoreProductDtoToProduct(fakeStoreProductDto1));
        }
        Optional<FakeStoreProductDto> fakeStoreProductDto= fakeStoreClient.getSingleProduct(productId);

//        fakeStoreProducts.put(productId,fakeStoreProductDto.get());
        redisTemplate.opsForHash().put(productId,"PRODUCTS",fakeStoreProductDto.get());
        if (fakeStoreProductDto.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(ProductConverter.convertFakeStoreProductDtoToProduct(fakeStoreProductDto.get()));
    }

    @Override
    public Product addNewProduct(ProductDto product) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.addNewProduct(product);
        return ProductConverter.convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {

        FakeStoreProductDto fakeStoreProductDto =  fakeStoreClient.updateProduct(productId, product);


        return ProductConverter.convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {

        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.replaceProduct(productId, product);

        return ProductConverter.convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }


    @Override
    public Integer deleteProduct(Long productId) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.deleteProduct(productId);
        return fakeStoreProductDto.getId().intValue();
    }

    @Override
    public Product save(Product product) {
        return null;
    }
}
