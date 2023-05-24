const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy : 'http://localhost:8080'
  }
})

// module.exports = defineConfig({
//   devServer: {
//     port: 8081,
//     historyApiFallback: true,
//     proxy: {
//       "/": {
//         target: "http://localhost:8080",
//         ws: true,
//         changeOrigin: true,
//         pathRewrite: {
//           "^/api": "",
//         },
//       },
//     },
//   },
// });