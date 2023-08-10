import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// https://vitejs.dev/config/
export default defineConfig({
  server:{
    host:true,
    strictPort:true,
    port:3001
  },
  plugins: [react()],
  resolve: {
    alias: {
      src: "/src",
      "axios/lib/adapters": "axios/lib/adapters/http",
    },
  },
});
