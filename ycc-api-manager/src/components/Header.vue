<template>
    <div class="my-header" 
     :style="defStyle">
        <slot></slot>
    </div>
</template>
<script>
export default {
    name:"my-header",
    data(){
        return {
            defStyle:{
                height:this.height,
                backgroundColor:this.bgColor
            }
        }
    },
    props:{
        height:{
            type:String,
            required:false,
            default:()=>"60px"
        },
         bgColor:{
            type:String,
            required:false,
            default:()=>"#3C8DBC"
        }

    }
}
</script>

<style lang="scss" >

</style>