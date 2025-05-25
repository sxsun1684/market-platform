const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    // Specify the base URL for production and development
    publicPath: process.env.NODE_ENV === 'production'
        ? '/market-platform/' // Replace '/my-project/' with your actual production base path
        : '/',


    indexPath: 'index.html',

    devServer: {
        port: 8080,
        proxy: {
            '/api': {
                target: 'http://localhost:9090', // backend Spring Boot address
                changeOrigin: true,
                pathRewrite: {
                    '^/api': '' // If the backend does not have the `/api` prefix, remove it.
                }
            }
        }
    },
    chainWebpack: config => {
        config.plugin('html')
            .tap(args => {
                args[0].title = "Bunny Market";
                return args;
            })
    },

})
