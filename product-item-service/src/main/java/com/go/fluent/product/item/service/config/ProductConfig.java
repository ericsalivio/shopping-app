package com.go.fluent.product.item.service.config;

import com.go.fluent.product.item.service.model.entity.ProductEntity;
import com.go.fluent.product.item.service.repository.ProductRepository;
import com.go.fluent.product.item.service.model.entity.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ProductConfig {

    public static final String NIKE_BASKETBALL = "Nike Basketball";
    public static final String ADIDAS_BASKETBALL = "Adidas Basketball";
    public static final String NEW_BALANCE_PH = "New Balance PH";
    @Autowired
    private ProductRepository productRepository;

    /*
       Setup the list of product items
       Insert the list of test data upon startup of service
    */
    @PostConstruct
    private void init(){
        List<ProductEntity> listOfProduct = new ArrayList<>();
        ProductEntity nikeExist =  productRepository.findByName(NIKE_BASKETBALL);
        ProductEntity adidasExist =  productRepository.findByName(ADIDAS_BASKETBALL);
        ProductEntity nbExist =  productRepository.findByName(NEW_BALANCE_PH);

        ProductEntity nike = new ProductEntity().builder()
                .name(NIKE_BASKETBALL)
                .description("Stay Active With The Best Of Nike to Keep You Positive, Healthy And Moving Forward.")

                .build();


        ProductEntity adidas = new ProductEntity().builder()
        .name(ADIDAS_BASKETBALL)
       .description("Adidas basketball will take your game to the next level")

                .build();


        ProductEntity newBalance = new ProductEntity().builder()
       .name(NEW_BALANCE_PH)
                .description("New Balance men's sneakers and athletic wear help you reach your greatest potential.")

                .build();



        List<ItemEntity> itemsNike = new ArrayList<>();
        ItemEntity lebron17 = new ItemEntity().builder()
                .name("Lebron 17 Low")
                .price(new BigDecimal(8095.00))
                .imageUrl("assets/images/shoes/lebron-17-low.jpg")
                .description("Fine-tuned for LeBron's ferocious game, the LeBron 17 Low gives the King another sensation to add to his arsenal.")
                .product(nike)
                .build();
        ItemEntity kobe11 = new ItemEntity().builder()
                .name("Kobe 11 Low")
                .price(new BigDecimal(8095.00))
                .imageUrl("assets/images/shoes/kobe-low.jpg")
                .description("Fine-tuned for Kobe's ferocious game, the LeBron 17 Low gives the King another sensation to add to his arsenal.")
                .product(nike)
                .build();

        itemsNike.add(kobe11);
        itemsNike.add(lebron17);


        List<ItemEntity> itemsAdidas = new ArrayList<>();
        ItemEntity drose773 = new ItemEntity().builder()
                .name("D-rose Low")
                .price(new BigDecimal(3800.00))
                .imageUrl("assets/images/shoes/drose-low.jpg")
                .description("Derrick Rose has a reputation for getting buckets with ease. Channel some of that laid-back D-Rose swagger every time you hit the hardwood in these adidas basketball shoes.")
                .product(adidas)
                .build();
        ItemEntity dame6 = new ItemEntity().builder()
                .name("Dame 6")
                .price(new BigDecimal(12800.00))
                .imageUrl("assets/images/shoes/dame-6.jpg")
                .description("Dame has a reputation for getting buckets with ease. Channel some of that laid-back D-Rose swagger every time you hit the hardwood in these adidas basketball shoes.")
                .product(adidas)
                .build();
        itemsAdidas.add(drose773);
        itemsAdidas.add(dame6);



        List<ItemEntity> itemsNewBalance = new ArrayList<>();
        ItemEntity nb574 = new ItemEntity().builder()
                .name("New Balance 574")
                .price(new BigDecimal(5095.00))
                .imageUrl("assets/images/shoes/nb574.jpg")
                .description("Lace up an athletic style that's both timeless and limitless with the new 574 Classic Athletic Shoe fron New Balance!")
                .product(newBalance)
                .build();
        ItemEntity nb997h = new ItemEntity().builder()
                .name("New Balance 997")
                .price(new BigDecimal(6095.00))
                .imageUrl("assets/images/shoes/nb997h.jpg")
                .description("Synthetic and mesh uppers offer durability and breathability.")
                .product(newBalance)
                .build();

        itemsNewBalance.add(nb574);
        itemsNewBalance.add(nb997h);

        adidas.setItems(itemsAdidas);
        newBalance.setItems(itemsNewBalance);
        nike.setItems(itemsNike);

        listOfProduct.add(nike);
        listOfProduct.add(adidas);
        listOfProduct.add(newBalance);

        if(Objects.isNull(nikeExist) && Objects.isNull(adidasExist) && Objects.isNull(nbExist)){
            productRepository.saveAll(listOfProduct);
        }

    }
}
