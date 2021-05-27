<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    {{title}}
    {{content}}
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}

export async function getBlog() {
    const response = await fetch('/api/blog');
    return await response.json();
}

</script>

<style>
</style>