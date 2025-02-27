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
                    <tiny-chart-bar :options="options2"></tiny-chart-bar>
                </div>
                <div class="h-100% w-40% rd-3xl flex">
                    <div class="w-50% h-100%">
                        <div class="w-100% h-50% gap-4">
                            <tiny-card class="border-10"
                                ><tiny-statistic :value="totalmoney">
                                    <template #prefix>
                                        <div style="margin-right: 15px"
                                            >调解成功案件</div
                                        >
                                    </template>
                                    <template #suffix>
                                        <div style="font-size: 24px">件</div>
                                    </template>
                                </tiny-statistic></tiny-card
                            >
                            <div class="h-5%"></div>
                            <tiny-card
                                ><tiny-statistic :value="totalyestask">
                                    <template #prefix>
                                        <div style="margin-right: 15px"
                                            >涉及金额</div
                                        >
                                    </template>
                                    <template #suffix>
                                        <div style="font-size: 24px">￥</div>
                                    </template>
                                </tiny-statistic></tiny-card
                            >
                            <div class="h-5%"></div>
                            <tiny-card
                                ><tiny-statistic :value="totaluser">
                                    <template #prefix>
                                        <div style="margin-right: 15px"
                                            >已完成案件</div
                                        >
                                    </template>
                                    <template #suffix>
                                        <div style="font-size: 24px">件</div>
                                    </template>
                                </tiny-statistic></tiny-card
                            >
                            <div class="h-5%"></div>
                            <tiny-card
                                ><tiny-statistic :value="totalapplicant">
                                    <template #prefix>
                                        <div style="margin-right: 15px"
                                            >未完成案件</div
                                        >
                                    </template>
                                    <template #suffix>
                                        <div style="font-size: 24px">件</div>
                                    </template>
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
import { ref, onMounted } from 'vue'
import { TinyHuichartsPie as TinyChartPie } from '@opentiny/vue'
import { TinyCard, TinyCarousel, TinyCarouselItem } from '@opentiny/vue'
import axios from 'axios'

const totalmoney = ref('8')
const totalyestask = ref('25')
const totaluser = ref('3')
const totalapplicant = ref('120')

const chartData = ref([
    { value: 100, name: '调解完成' },
    { value: 90, name: '待分配调解员' },
    { value: 49, name: '预约时间' },
    { value: 14, name: '已分配调解员' },
])
const monthData = ref([
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
])

const options3 = ref({
    type: 'pie',
    selectedMode: 'multipe',
    data: chartData,
})

const options2 = ref({
    data: [
        { 日期: '王生', 受理案件: 21, 调解成功案件: 14 },
        { 日期: '王适', 受理案件: 33, 调解成功案件: 17 },
        { 日期: '徐金陵', 受理案件: 75, 调解成功案件: 44 },
        { 日期: '李玖', 受理案件: 87, 调解成功案件: 53 },
        { 日期: '李靖', 受理案件: 93, 调解成功案件: 63 },
    ],
    xAxis: {
        data: '日期',
    },
    direction: 'horizontal', // 横向柱状图
})
const options = ref({
    data: monthData,
    xAxis: {
        data: 'Month',
    },
    yAxis: {
        name: '数量',
    },
})

const monthMap = {
    1: 'Jan',
    2: 'Feb',
    3: 'Mar',
    4: 'Apr',
    5: 'May',
    6: 'Jun',
    7: 'Jul',
    8: 'Aug',
    9: 'Sep',
    10: 'Oct',
    11: 'Nov',
    12: 'Dec',
}

const mediationStatusMap = {
    0: '待分配调解员',
    1: '已分配调解员',
    2: '预约时间',
    3: '调解完成',
    4: '调解失败',
}

const fetchChartData = async () => {
    const res = await axios.get('/ls/logic/statistics/get_status')
    if (res.data.code === 200) {
        chartData.value = res.data.data.map((item) => ({
            name: mediationStatusMap[item.status],
            value: item.case_count,
        }))
    }
}

const fetchMonthData = async () => {
    const res = await axios.get('/ls/logic/statistics/get_month?year=2024')
    if (res.data.code === 200) {
        monthData.value = res.data.data.map((item) => ({
            Month: monthMap[item.month],
            Value: item.case_count,
        }))
    }
}

const req = async function get(type) {
    const res = await axios.get(
        '/ls/logic/statistics/get_informations?type=' + type
    )
    return res.data.data
}

const fetchInfomationData = async () => {
    let task = []
    task.push(
        req('1').then((data) => {
            totalyestask.value = data
        })
    )
    task.push(
        req('2').then((data) => {
            totalmoney.value = data
        })
    )
    task.push(
        req('3').then((data) => {
            totaluser.value = data
        })
    )
    task.push(
        req('4').then((data) => {
            totalapplicant.value = data
        })
    )
    await Promise.all(task)
}

onMounted(() => {
    fetchChartData()
    fetchMonthData()
    fetchInfomationData()
})
</script>

<style scoped></style>
