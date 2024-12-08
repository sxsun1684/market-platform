const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    // Specify the base URL for production and development
    publicPath: process.env.NODE_ENV === 'production'
        ? '/market-platform/' // Replace '/my-project/' with your actual production base path
        : '/',

    devServer: {
        port: 8080
    },
    chainWebpack: config => {
        config.plugin('html')
            .tap(args => {
                args[0].title = "Bunny Market";
                return args;
            })
    },

})
