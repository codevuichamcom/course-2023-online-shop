import { useEffect, useState } from "react";
import { Banner, Offer, ProductListWithIntro, Slide } from "./components";
import { axiosClient } from "src/axios/AxiosClient";
export const HomePage = () => {
  const [trendingProductList, setTrendingProductList] = useState([]);
  const [bestSellerProductList, setBestSellerProductList] = useState([]);
  useEffect(() => {
    const fetchData = async () => {
      const { data: trendingProductList } = await axiosClient.get(
        "/products/trending"
      );
      setTrendingProductList(trendingProductList);

      const { data: bestSellerProductList } = await axiosClient.get(
        "/products/best-seller"
      );
      setBestSellerProductList(bestSellerProductList);
    };
    fetchData();
  }, []);
  return (
    <>
      <main className="home-page__main">
        <Banner />
        <Slide />
        <ProductListWithIntro
          description="Popular Item in the market"
          title="Trending"
          name="Product"
          products={trendingProductList}
        />
        <Offer />
        <ProductListWithIntro
          description="Popular Item in the market"
          title="Best"
          name="Sellers"
          products={bestSellerProductList}
        />
      </main>
    </>
  );
};
