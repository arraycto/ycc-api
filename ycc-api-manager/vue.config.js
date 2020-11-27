module.exports = {
    publicPath:"/",
    outputDir:"build",
    assetsDir:"static/statics",

    devServer:{
        proxy:{
            "/ycc-api-admin":{
                target:"http://localhost:8099"
            }
        }
    },

    css:{
        loaderOptions:{
            scss: {
                additionalData: `@import "@/assets/globalStyle.scss";`
            }
        }
    }
  
}   