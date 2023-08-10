import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { accountReducer } from "./feature/account/AccountSlice";
import { cartReducer } from "./feature/cart/CartSlice";
export const store = configureStore({
  reducer: combineReducers({
    account: accountReducer,
    cart: cartReducer,
  }),
});
