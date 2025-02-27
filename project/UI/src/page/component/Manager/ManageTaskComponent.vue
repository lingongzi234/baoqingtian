<template>
  <div class="preview">
    <tiny-nav-menu :data="menuData" allow-full-url></tiny-nav-menu>
  </div>
  <div><router-view></router-view></div>
</template>
<script setup>
import { TinyNavMenu } from "@opentiny/vue";
import { ref } from "vue";
const menuData = ref([
  {
    title: '受理案件',
    url: '/manageyescomponent'
  },
  {
    title: '未受理案件',
    url: '/managenotaskcomponent'
  }
])

</script>
<style scoped></style>
