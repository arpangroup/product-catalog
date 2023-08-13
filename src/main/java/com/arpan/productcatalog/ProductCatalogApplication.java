package com.arpan.productcatalog;

import com.arpan.productcatalog.entity.Catalog;
import com.arpan.productcatalog.entity.Store;
import com.arpan.productcatalog.entity.product.Category;
import com.arpan.productcatalog.repository.CatalogRepository;
import com.arpan.productcatalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class ProductCatalogApplication implements CommandLineRunner {
	@Autowired CatalogRepository catalogRepository;
	@Autowired CatalogService catalogService;

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Customer customer = new Customer(1, "John Doe");
		System.out.println("##########################");
		System.out.println(customer);
		System.out.println(customer.getFullName());
		catalogService.getAllCatalogSummary(1252L).forEach(System.out::println);
		//catalogRepository.save(generateDefaultCatalog("MyStore"));
		System.out.print("Catalog: ");
		catalogRepository.findAll().stream().map(Catalog::getCatalogName).forEach(System.out::println);
		System.out.print("Categories: ");
		catalogRepository.findAll().stream().map(Catalog::getCategories).flatMap(Collection::stream).map(cat -> cat.getId() + cat.getName()).forEach(System.out::println);
		System.out.println("##########################");
	}


	private Catalog generateDefaultCatalog(String storeName) {
		// Create a default Catalog
		Catalog catalog = new Catalog(storeName + "-catalog");
		catalog.setDefault(true);
		catalog.setActive(true);
		catalog.setOwnerId("0");
		catalog.setOwnerName("DEFAULT");

		// Create and attach Default Category to the catalog
		Category category = new Category("Products");
		catalog.attachCategory(category);

		return catalog;
	}
}

