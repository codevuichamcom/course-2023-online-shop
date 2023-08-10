package com.funnycode.onlineshop.repository.criteria;

import com.funnycode.onlineshop.dto.PagingDTOResponse;
import com.funnycode.onlineshop.dto.ProductDTOFilter;
import com.funnycode.onlineshop.dto.ProductDTOResponse;
import com.funnycode.onlineshop.entity.Product;
import com.funnycode.onlineshop.mapper.ProductMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductCategoryCriteria {
    EntityManager em;

    public PagingDTOResponse search(ProductDTOFilter productDTOFilter) {
        StringBuilder sql = new StringBuilder("select p from Product p where 1=1");
        Map<String, Object> params = new HashMap<>();//Để lưu param và value tương ứng

        //Dynamic query
        if (productDTOFilter.getCategoryId() != null) {
            sql.append(" and p.category.id =:categoryId ");
            params.put("categoryId", productDTOFilter.getCategoryId());
        }
        if (productDTOFilter.getBrandId() != null) {
            sql.append(" and p.category.id =:brandId ");
            params.put("brandId", productDTOFilter.getBrandId());
        }
        if (productDTOFilter.getColorId() != null) {
            sql.append(" and p.color.id =:colorId ");
            params.put("colorId", productDTOFilter.getColorId());
        }
        sql.append(" and p.price between :priceFrom and :priceTo ");
        params.put("priceFrom", productDTOFilter.getPriceFrom());
        params.put("priceTo", productDTOFilter.getPriceTo());
        sql.append(" and p.name like :name");
        params.put("name", "%"+productDTOFilter.getName()+"%");

        Query countQuery = em.createQuery(sql.toString().replace("select p", "select count(p.id)"));

        if (productDTOFilter.getSortByPrice() != null) {
            sql.append(" order by p.price ").append(productDTOFilter.getSortByPrice());
        }

        Integer pageIndex = productDTOFilter.getPageIndex();
        Integer pageSize = productDTOFilter.getPageSize();

        TypedQuery<Product> productTypedQuery = em.createQuery(sql.toString(), Product.class);

        // Set param to query
        params.forEach((k, v) -> {
            productTypedQuery.setParameter(k, v);
            countQuery.setParameter(k, v);
        });

        // Paging
        productTypedQuery.setFirstResult((pageIndex - 1) * pageSize);
        productTypedQuery.setMaxResults(pageSize);
        List<Product> listProduct = productTypedQuery.getResultList();

        long totalProduct = (long) countQuery.getSingleResult();
        long totalPage = totalProduct / pageSize;
        if (totalProduct % pageSize != 0) {
            totalPage++;
        }

        List<ProductDTOResponse> productDTOResponseList = listProduct.stream().map(ProductMapper::toProductDTOResponse).collect(Collectors.toList());
        return PagingDTOResponse.builder()
                .totalElements(totalProduct)
                .totalPage(totalPage)
                .data(productDTOResponseList)
                .build();
    }
}
