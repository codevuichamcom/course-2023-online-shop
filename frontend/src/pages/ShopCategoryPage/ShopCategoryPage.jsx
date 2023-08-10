import { useEffect, useState } from "react";
import { Col, Container, Input, Row } from "reactstrap";
import { axiosClient } from "src/axios/AxiosClient";
import {
  BannerPath,
  PaginationComponent,
  ProductList,
  SelectBoxCustom,
} from "src/components";
import "./ShopCategoryPage.css";
import { RadioList } from "./components";

const sortOptions = [
  { key: "asc", value: "Price ASC" },
  { key: "desc", value: "Price DESC" },
];
export const ShopCategoryPage = () => {
  const [categories, setCategories] = useState([]);
  const [brands, setBrands] = useState([]);
  const [colors, setColors] = useState([]);
  const [productPage, setProductPage] = useState({
    data: [],
    totalElements: 0,
    totalPage: 0,
  });
  const [searchParams, setSearchParams] = useState({
    categoryId: null,
    brandId: null,
    colorId: null,
    sortByPrice: null,
    name: null,
    priceFrom: null,
    priceTo: 10000,
    pageIndex: 1,
    pageSize: 1,
  });
  useEffect(() => {
    const fetchData = async () => {
      const { data: categories } = await axiosClient.get("/categories");
      setCategories(
        categories.map((category) => {
          return {
            id: category.id,
            name: category.categoryName,
            type: "Category",
          };
        })
      );
      const { data: brands } = await axiosClient.get("/brands");
      setBrands(
        brands.map((brand) => {
          return { id: brand.id, name: brand.branchName, type: "Brand" };
        })
      );
      const { data: colors } = await axiosClient.get("/colors");
      setColors(
        colors.map((color) => {
          return { id: color.id, name: color.colorName, type: "Color" };
        })
      );

      const { data: productPage } = await axiosClient.get("/products/search", {
        params: searchParams,
      });
      setProductPage(productPage);
    };
    fetchData();
  }, []);

  const searchProducts = async (params) => {
    const { data: productPage } = await axiosClient.get("/products/search", {
      params,
    });
    setProductPage(productPage);
  };

  useEffect(() => {
    if (searchParams) {
      if (searchParams.sortByPrice === "default") {
        searchParams.sortByPrice = null;
      }
      searchProducts(searchParams);
    }
  }, [searchParams]);
  return (
    <>
      <main className="shop-category-page__main">
        <BannerPath title="Shop Category" path="Home - Shop Category" />
        <Container className="shop-category-page__container">
          <Row>
            <Col md="5" lg="4" xl="3">
              <div className="sidebar">
                <div className="sidebar__header">Browse Categories</div>
                <div className="sidebar__main">
                  <RadioList
                    selected={searchParams.categoryId}
                    onSelect={(categoryId) => {
                      setSearchParams({ ...searchParams, categoryId });
                    }}
                    data={categories}
                  />
                </div>
              </div>
              <div className="sidebar mt-4">
                <div className="sidebar__header">Product Filter</div>
                <div className="sidebar__main">
                  <div className="sidebar__title">Brands</div>
                  <RadioList
                    selected={searchParams.brandId}
                    onSelect={(brandId) => {
                      setSearchParams({ ...searchParams, brandId });
                    }}
                    data={brands}
                  />
                </div>
                <div className="sidebar__main">
                  <div className="sidebar__title">Colors</div>
                  <RadioList
                    selected={searchParams.colorId}
                    onSelect={(colorId) => {
                      setSearchParams({ ...searchParams, colorId });
                    }}
                    data={colors}
                  />
                </div>
                <div className="sidebar__main">
                  <div className="sidebar__title">Price</div>
                  <div className="sidebar__range">
                    <Input
                      className="sidebar__range__from"
                      placeholder="From"
                      value={searchParams.priceFrom}
                      onChange={(e) => {
                        setSearchParams({
                          ...searchParams,
                          priceFrom: parseInt(e.target.value),
                        });
                      }}
                    />
                    <Input
                      className="sidebar__range__to ms-2"
                      placeholder="To"
                      value={searchParams.priceTo}
                      onChange={(e) => {
                        setSearchParams({
                          ...searchParams,
                          priceTo: parseInt(e.target.value),
                        });
                      }}
                    />
                  </div>
                </div>
              </div>
            </Col>
            <Col md="7" lg="8" xl="9">
              <div className="filter-bar d-flex align-items-center flex-wrap">
                <SelectBoxCustom
                  className="filter-bar__sort w-25"
                  data={sortOptions}
                  selected={searchParams.sortByPrice}
                  onSelectBoxChange={(sortByPrice) => {
                    setSearchParams({ ...searchParams, sortByPrice });
                  }}
                />
                <Input
                  className="filter-bar__search ms-auto"
                  bsSize="sm"
                  type="search"
                  placeholder="Search here..."
                  value={searchParams.name}
                  onChange={(e) => {
                    setSearchParams({ ...searchParams, name: e.target.value });
                  }}
                />
              </div>
              <ProductList products={productPage.data} xl="3" />
              <div className="shop-category-page_pagination">
                <PaginationComponent
                  pageIndex={searchParams.pageIndex}
                  onPageChange={(pageIndex) => {
                    setSearchParams({ ...searchParams, pageIndex });
                  }}
                  totalPage={productPage.totalPage}
                />
              </div>
            </Col>
          </Row>
        </Container>
      </main>
    </>
  );
};
