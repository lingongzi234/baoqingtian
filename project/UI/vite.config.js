import { defineConfig } from 'vite'
import UnoCSS from 'unocss/vite'
import vue from '@vitejs/plugin-vue'
// https://vite.dev/config/
export default defineConfig({
    plugins: [vue(), UnoCSS()],
    server: {
        host: true,
        proxy: {
            '/custom': {
                target: 'http://127.0.0.1:8081/',
                changeOrigin: true,
            },
            '/ls': {
                target: 'http://127.0.0.1:8081/',
                changeOrigin: true,
            },
        },
    },
})
