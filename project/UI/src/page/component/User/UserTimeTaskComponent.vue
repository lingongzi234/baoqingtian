

<template>
  <div class="w-full h-8% text-align-center text-10"></div>
  <div class="w-full h-7% text-align-center text-10"></div>
  <div class="flex"><div class="w-15% h-60%"></div>
    <div class="w-60% h-60%"><tiny-calendar-view :events="eventslist" :year="2025" :month="1" ></tiny-calendar-view></div></div>

</template>
<script setup>
import { ref } from 'vue'
import { TinyCalendarView } from '@opentiny/vue'

const eventslist = ref([
  {
    title: '经济纠纷调解',
    start: '2025-01-19 8:30:00',
    end: '2023-01-19 9:00:00',
    content: '线下调解，地址在大楼A108',
    theme: 'blue'
  },
  {
    title: '财产纠纷调解',
    start: '2025-01-13 8:30:00',
    end: '2023-01-13 11:30:00',
    content: '音视频调解',
    theme: 'purple'
  },
  {
    title: '借款纠纷调解',
    start: '2025-01-15 8:30:00',
    end: '2023-01-15 11:30:00',
    content: '音视频调解',
    theme: 'purple'
  },
  {
    title: '借款纠纷调解',
    start: '2025-01-20 8:30:00',
    end: '2023-01-20 11:30:00',
    content: '线下调解，地址在大楼b306',
    theme: 'blue'
  },
  {
    title: '还款纠纷调解',
    start: '2025-01-25 8:30:00',
    end: '2023-01-25 11:30:00',
    content: '音视频调解',
    theme: 'purple'
  },
  {
    title: '贷款纠纷调解',
    start: '2025-01-23 8:30:00',
    end: '2023-01-23 11:30:00',
    content: '线下调解，地址在大楼c210',
    theme: 'blue'
  },
  {
    title: '工资纠纷调解',
    start: '2025-01-29 8:30:00',
    end: '2023-01-29 11:30:00',
    content: '音视频调解',
    theme: 'purple'
  },
  {
    title: '财产纠纷调解',
    start: '2025-01-17 8:30:00',
    end: '2023-01-17 11:30:00',
    content: '线下调解，地址在大楼a112',
    theme: 'blue'
  }
])
</script>
<style scoped>

</style>
