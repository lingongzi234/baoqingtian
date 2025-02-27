<template>
  <div class="flex justify-start gap-4">
    <span class="font-bold whitespace-nowrap">{{ label }}:</span>
    <span>{{ value }}</span>
  </div>
</template>
<script setup>
defineProps(['label', 'value'])
</script>