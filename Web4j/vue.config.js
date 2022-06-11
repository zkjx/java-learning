let proxyObj={}

proxyObj['/'] = {
    //websocket服务器地址
    ws: false,
    //代理地址
    target: 'http://localhost:8081',
    //是否跨域
    changeOrigin: true,
    //是否开启路径变量
    pathRewrite: {
        '^/': ''
    }

}


module.exports = {
  // 选项...
  devServer: {
      host: 'localhost',
      port: 8080,
      proxy: proxyObj
  }
}
