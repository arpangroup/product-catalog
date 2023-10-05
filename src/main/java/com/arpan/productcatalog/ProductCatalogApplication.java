package com.arpan.productcatalog;

import com.arpan.productcatalog.dto.request.StoreCreateRequest;
import com.arpan.productcatalog.entity.Catalog;
import com.arpan.productcatalog.entity.product.Category;
import com.arpan.productcatalog.exception.ValidationException;
import com.arpan.productcatalog.repository.CatalogRepository;
import com.arpan.productcatalog.service.CatalogService;
import com.arpan.productcatalog.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Collection;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class ProductCatalogApplication implements CommandLineRunner {
	@Autowired CatalogRepository catalogRepository;
	@Autowired CatalogService catalogService;
	@Autowired StoreService storeService;

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("##########################");
//		initData();
//		new FieldMapper().print();
		System.out.println("##########################");
	}

	private void initData() {
		Customer customer = new Customer(1, "John Doe");
		System.out.println(customer);
		System.out.println(customer.getFullName());
		catalogService.getAllCatalogSummary(1252L).forEach(System.out::println);
		//catalogRepository.save(generateDefaultCatalog("MyStore"));
		System.out.print("Catalog: ");
		catalogRepository.findAll().stream().map(Catalog::getName).forEach(System.out::println);
		System.out.print("Categories: ");
		catalogRepository.findAll().stream().map(Catalog::getCategories).flatMap(Collection::stream).map(cat -> cat.getId() + cat.getName()).forEach(System.out::println);
		System.out.println("##########################");
		//createStore();
	}


	private void createStore() throws ValidationException {
		System.out.print("creating store............");
		storeService.createStore(new StoreCreateRequest("Store1"));
		storeService.createStore(new StoreCreateRequest("Store2"));
	}


	private Catalog generateDefaultCatalog(String storeName) {
		// Create a default Catalog
		Catalog catalog = new Catalog(storeName + "-catalog");
		catalog.setIsDefault(true);
		catalog.setIsActive(true);
		catalog.setOwnerId("0");
		catalog.setOwnerName("DEFAULT");

		// Create and attach Default Category to the catalog
		Category category = new Category("Products");
		catalog.attachCategory(category);

		return catalog;
	}
}

