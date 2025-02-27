<template>
  <div class="preview">
    <tiny-nav-menu :data="menuData" allow-full-url></tiny-nav-menu>
  </div>
  <div><router-view></router-view></div>
</template>
<script setup>
import {TinyNavMenu} from "@opentiny/vue";
import {ref} from "vue";
const menuData = ref([
  {
    title: '未完结案件',
    url: '/usernotaskcomponent'
  },
  {
    title: '已完结案件',
    url: '/useryestaskcomponent'
  }
])

</script>
<style scoped>

</style>
