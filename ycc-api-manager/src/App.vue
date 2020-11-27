<template>
  <div id="app">
    <transition name="fade">
     <router-view></router-view>

    </transition>
  </div>
</template>

<script>

export default {
  name: 'App',
  components: {
  },
  mounted() {

  }
}
</script>
<style lang="scss" scoped>
   #app{
      
   }
</style>