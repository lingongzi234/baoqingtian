<template>
    <div class="w-screen h-screen flex bg-#E6F2FF gap-1%">
        <div class="w-50% h-100% gap-2%">
            <div class="w-100% h-15%"></div>
            <div class="w-100% h-full"
                ><tiny-calendar-view
                    :events="eventslist"
                    :year="2025"
                    :month="1"
                ></tiny-calendar-view>
            </div>
        </div>
        <div class="w-50% h-100%">
            <div class="w-full h-10%"></div>
            <div class="w-130 h-40%">
                <tiny-chart-pie :options="options4"></tiny-chart-pie>
            </div>
            <div class="w-100% h-50% flex">
                <div class="w-10% h-full"></div>
                <div class="w-90% h-full">
                    <div class="h-10%"></div>
                    <tiny-card class="border-10"
                        ><tiny-statistic :value="totalmoney">
                            <template #prefix>
                                <div style="margin-right: 15px"
                                    >机构涉及金額</div
                                >
                            </template>
                            <template #suffix>
                                <div style="font-size: 24px">￥</div>
                            </template>
                        </tiny-statistic></tiny-card
                    >
                    <div class="h-5%"></div>
                    <tiny-card
                        ><tiny-statistic :value="totalyestask">
                            <template #prefix>
                                <div style="margin-right: 15px"
                                    >共调解成功案件</div
                                >
                            </template>
                            <template #suffix>
                                <div style="font-size: 24px">件</div>
                            </template>
                        </tiny-statistic></tiny-card
                    >
                    <div class="h-5%"></div>
                    <tiny-card
                        ><tiny-statistic :value="totaluser">
                            <template #prefix>
                                <div style="margin-right: 15px"
                                    >平台入驻调解员</div
                                >
                            </template>
                            <template #suffix>
                                <div style="font-size: 24px">位</div>
                            </template>
                        </tiny-statistic></tiny-card
                    >
                    <div class="h-5%"></div>
                </div>
            </div>
        </div>
    </div>
</template>
<script setup>
import {
    TinyCalendarView,
    TinyHuichartsWordcloud as TinyChartWordcloud,
} from '@opentiny/vue'
import {
    TinyStatistic,
    TinyHuichartsHistogram as TinyChartHistogram,
} from '@opentiny/vue'
import { ref } from 'vue'
import { TinyHuichartsPie as TinyChartPie } from '@opentiny/vue'
import { TinyCard, TinyCarousel, TinyCarouselItem } from '@opentiny/vue'

const totalmoney = ref('277,238')
const totalyestask = ref('25')
const totaluser = ref('3')
const totalapplicant = ref('120')

const eventslist = ref([
    {
        title: '经济纠纷调解',
        start: '2025-01-20 8:30:00',
        end: '2023-01-20 9:00:00',
        content: '线下调解，地址在大楼A108',
        theme: 'blue',
    },
    {
        title: '还款纠纷调解',
        start: '2025-01-23 8:30:00',
        end: '2023-01-23 11:30:00',
        content: '音视频调解',
        theme: 'purple',
    },
    {
        title: '贷款纠纷调解',
        start: '2025-01-28 8:30:00',
        end: '2023-01-28 11:30:00',
        content: '线下调解，地址在大楼c210',
        theme: 'blue',
    },
    {
        title: '工资纠纷调解',
        start: '2025-01-30 8:30:00',
        end: '2023-01-30 11:30:00',
        content: '音视频调解',
        theme: 'purple',
    },
])

const options4 = ref({
    type: 'pie',
    selectedMode: 'multipe',
    data: [
        { value: 5, name: '调解完成' },
        { value: 1, name: '待分配调解员' },
        { value: 2, name: '预约时间' },
        { value: 1, name: '已分配调解员' },
    ],
})
</script>

<style scoped></style>
