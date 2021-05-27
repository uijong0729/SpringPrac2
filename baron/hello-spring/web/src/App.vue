<template>
  <HelloWorld msg="{{title}}"/>
  

</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  },
  data() {
    return {
      title : "",
      content : ""
    }
  },
  methods:{
    getBlog(){
      getBlog().then(response => {
        console.log(response);
        this.title = response.title
        this.content = response.content
      })
    }
  },
  mounted(){
    this.getBlog();
  }
}
</script>

<style>
</style>
