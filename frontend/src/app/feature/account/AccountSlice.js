import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  loggedIn: {
    id: 0,
    username: "",
    email: "",
    avatar: "",
  },
};

export const AccountSlice = createSlice({
  name: "account",
  initialState,
  reducers: {
    setLoggedInAccount(state, action) {
      state.loggedIn = action.payload;
    },
  },
});

// Action creators are generated for each case reducer function
export const { setLoggedInAccount } = AccountSlice.actions;

const accountReducer = AccountSlice.reducer;
export { accountReducer };
