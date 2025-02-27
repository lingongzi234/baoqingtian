<template>
    <div class="w-screen h-screen flex gap-1%">
        <div class="h-full w-90% bg-#E6F2FF">
            <div class="w-full h-40% flex gap-2%">
                <div class="h-100% w-50%"
                    ><tiny-chart-histogram
                        :options="options"
                    ></tiny-chart-histogram
                ></div>
                <div class="h-100% w-50%"
                    ><tiny-chart-pie :options="options3"></tiny-chart-pie
                ></div>
            </div>
            <div class="w-full h-10%"></div>
            <div class="w-full h-40% flex gap-2%">
                <div class="h-100% w-60%">
                    <tiny-chart-bar :options="options2"></tiny-chart-bar
                ></div>
                <div class="h-100% w-40% rd-3xl flex">
                    <div class="w-50% h-100%">
                        <div class="w-100% h-50% gap-4">
                            <tiny-card class="border-10"
                                ><tiny-statistic :value="totalmoney">
                                    <template #prefix
                                        ><div style="margin-right: 15px"
                                            >机构共已追回</div
                                        ></template
                                    >
                                    <template #suffix
                                        ><div style="font-size: 24px"
                                            >￥</div
                                        ></template
                                    >
                                </tiny-statistic></tiny-card
                            >
                            <div class="h-5%"></div>
                            <tiny-card
                                ><tiny-statistic :value="totalyestask">
                                    <template #prefix
                                        ><div style="margin-right: 15px"
                                            >平台共调解成功案件</div
                                        ></template
                                    >
                                    <template #suffix
                                        ><div style="font-size: 24px"
                                            >件</div
                                        ></template
                                    >
                                </tiny-statistic></tiny-card
                            >
                            <div class="h-5%"></div>
                            <tiny-card
                                ><tiny-statistic :value="totaluser">
                                    <template #prefix
                                        ><div style="margin-right: 15px"
                                            >平台入驻调解员</div
                                        ></template
                                    >
                                    <template #suffix
                                        ><div style="font-size: 24px"
                                            >位</div
                                        ></template
                                    >
                                </tiny-statistic></tiny-card
                            >
                            <div class="h-5%"></div>
                            <tiny-card
                                ><tiny-statistic :value="totalapplicant">
                                    <template #prefix
                                        ><div style="margin-right: 15px"
                                            >已涉及申请人</div
                                        ></template
                                    >
                                    <template #suffix
                                        ><div style="font-size: 24px"
                                            >位</div
                                        ></template
                                    >
                                </tiny-statistic></tiny-card
                            >
                        </div>
                    </div>
                    <div class="w-50% h-100%"></div>
                </div>
            </div>
        </div>
    </div>
</template>
<script setup>
import { TinyHuichartsBar as TinyChartBar } from '@opentiny/vue'
import {
    TinyStatistic,
    TinyHuichartsHistogram as TinyChartHistogram,
} from '@opentiny/vue'
import { ref } from 'vue'
import { TinyHuichartsPie as TinyChartPie } from '@opentiny/vue'
import { TinyCard, TinyCarousel, TinyCarouselItem } from '@opentiny/vue'

const totalmoney = ref('130,277,238')
const totalyestask = ref('25')
const totaluser = ref('3')
const totalapplicant = ref('120')

const options3 = ref({
    type: 'pie',
    selectedMode: 'multipe',
    data: [
        { value: 100, name: '调解完成' },
        { value: 90, name: '待分配调解员' },
        { value: 49, name: '预约时间' },
        { value: 14, name: '已分配调解员' },
    ],
})

const options2 = ref({
    data: [
        { 日期: '李六', 受理案件: 21, 调解成功案件: 14 },
        { 日期: '王五', 受理案件: 33, 调解成功案件: 17 },
        { 日期: '李四', 受理案件: 75, 调解成功案件: 44 },
        { 日期: '张三', 受理案件: 87, 调解成功案件: 53 },
        { 日期: '乐乐乐', 受理案件: 93, 调解成功案件: 63 },
    ],
    xAxis: {
        data: '日期',
    },
    direction: 'horizontal', // 横向柱状图
})
const options = ref({
    data: [
        { Month: 'Jan', Value: 100 },
        { Month: 'Feb', Value: 122 },
        { Month: 'Mar', Value: 323 },
        { Month: 'Apr', Value: 23 },
        { Month: 'May', Value: 232 },
        { Month: 'Jun', Value: 323 },
        { Month: 'Jul', Value: 323 },
        { Month: 'Aug', Value: 23 },
        { Month: 'Sep', Value: 23 },
        { Month: 'Oct', Value: 23 },
        { Month: 'Nov', Value: 23 },
        { Month: 'Dec', Value: 323 },
    ],
    xAxis: {
        data: 'Month',
    },
    yAxis: {
        name: '数量',
    },
})
</script>

<style scoped></style>
