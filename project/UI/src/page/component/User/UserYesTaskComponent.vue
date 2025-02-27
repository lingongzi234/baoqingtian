<template>
    <div class="w-230 h-118.5 pos-relative top-40 left-50">
        <tiny-grid
            :data="filtered1Data"
            :highlight-hover-row="true"
            :stripe="true"
            :edit-config="{ trigger: 'click', mode: 'cell', showStatus: true }"
            class="w-100% h-100% rounded-3xl"
        >
            <tiny-grid-column
                field="caseId"
                width="130"
                title="调解编号"
            ></tiny-grid-column>
            <tiny-grid-column
                field="appointmentTime"
                width="230"
                title="调解时间"
            ></tiny-grid-column>
            <tiny-grid-column
                field="mediationStatus"
                title="调解结果"
                width="230"
            ></tiny-grid-column>
            <tiny-grid-column title="调解协议书" width="150">
                <template #default="{ row }">
                    <!-- 详情按钮 -->
                    <button
                        @click="viewpicture(row)"
                        class="w-15 h-15 hover-bg-blue"
                        >查看</button
                    >
                </template>
            </tiny-grid-column>
            <tiny-grid-column title="操作" width="150">
                <template #default="{ row }">
                    <!-- 详情按钮 -->
                    <button
                        @click="viewDetails(row)"
                        class="w-15 h-15 hover-bg-blue"
                        >详情</button
                    >
                </template>
            </tiny-grid-column>
        </tiny-grid>
    </div>
    <div class="w-100% h-30%">
        <tiny-pager
            mode="number"
            layout="prev, pager, next, slot"
            :page-size="pageSize"
            :page-sizes="[5, 7, 10, 20, 50]"
            :total="total"
            v-model:current-page="currentPage"
            class="pos-absolute top-171 left-190"
        ></tiny-pager>
        <div class="pos-absolute top-43 left-117"
            ><tiny-base-select
                v-model="selectedappointmentTime"
                class="w-20%"
                :searchable="true"
                style="width: 110px"
                placeholder="调解时间"
                placement="top"
            >
                <tiny-option
                    v-for="(appointmentTime, index) in case1Options"
                    :key="appointmentTime.value"
                    :label="appointmentTime.label"
                    :value="appointmentTime.value"
                    :icon="appointmentTime.icon"
                >
                </tiny-option></tiny-base-select
        ></div>
        <div class="pos-absolute top-43 left-148"
            ><tiny-base-select
                v-model="selectedmediationStatus"
                class="w-20%"
                :searchable="true"
                style="width: 110px"
                placeholder="调解结果"
                placement="top"
            >
                <tiny-option
                    v-for="(mediationStatus, index) in case3Options"
                    :key="mediationStatus.value"
                    :label="mediationStatus.label"
                    :value="mediationStatus.value"
                    :icon="mediationStatus.icon"
                >
                </tiny-option></tiny-base-select
        ></div>
    </div>
    <tiny-dialog-box
        v-model:visible="dialogVisible"
        title="案件详情"
        width="50%"
        main-height="100%"
    >
        <template #default>
            <div class="flex justify-start gap-12">
                <div class="w-49%">
                    <ProfileSection>
                        <ProfileField
                            label="被申请人姓名"
                            :value="detailed.respondentName"
                        />
                        <ProfileField
                            label="联系方式"
                            :value="detailed.respondentPhoneNumber"
                        />
                        <ProfileField
                            label="身份证号"
                            :value="detailed.respondentIdNumber"
                        />
                        <ProfileField
                            label="申请机构名称"
                            :value="detailed.respondentInstructName"
                        />
                        <ProfileField
                            label="联系方式"
                            :value="detailed.respondentInstructPhonenumber"
                        />
                        <ProfileField
                            label="案件描述"
                            :value="detailed.caseDescribe"
                        />
                        <ProfileField
                            label="案由"
                            :value="detailed.caseReason"
                        />
                    </ProfileSection>
                </div>
                <tiny-divider
                    class="divider"
                    direction="vertical"
                    :style="{ height: '400px', borderTopWidth: '5px' }"
                ></tiny-divider>
                <div class="w-49%">
                    <ProfileSection>
                        <ProfileField
                            label="申请机构代理人姓名"
                            :value="detailed.respondentInstructPeopleName"
                        />
                        <ProfileField
                            label="联系方式"
                            :value="
                                detailed.respondentInstructPeoplePhoneNumber
                            "
                        />
                        <ProfileField
                            label="身份证号"
                            :value="detailed.respondentInstructPeopleIdNumber"
                        />
                        <ProfileField
                            label="证件类型"
                            :value="detailed.respondentInstructPeopleIdType"
                        />
                        <ProfileField
                            label="证件号码"
                            :value="detailed.respondentInstructPeopleIdNumber"
                        />
                        <ProfileField
                            label="应还总金额"
                            :value="detailed.totalAmount"
                        />
                    </ProfileSection>
                </div>
            </div>
        </template>
    </tiny-dialog-box>
    <tiny-dialog-box
        v-model:visible="showpicture"
        title="调解协议书"
        width="50%"
        main-height="100%"
    >
        <div>
            <img alt="pic" src="/img_3.png" />
        </div>
    </tiny-dialog-box>
</template>
<script setup>
import {
    TinyBaseSelect,
    TinyDialogBox,
    TinyDivider,
    TinyGrid,
    TinyGridColumn,
    TinyOption,
    TinyPager,
    Modal,
} from '@opentiny/vue'
import { computed, ref, onMounted } from 'vue'
import ProfileField from '../profile/ProfileField.vue'
import ProfileSection from '../profile/ProfileSection.vue'
import axios from 'axios'

const tableData = ref([
    {
        caseId: '5',
        appointmentTime: '2025-01-12 09:00:00',
        mediationMethod: '线上调解',
        mediationStatus: '调解成功',
        repaymentStatus: '未还款',
    },
    {
        caseId: '10',
        appointmentTime: '2025-01-13 10:30:00',
        mediationMethod: '线下面对面调解',
        mediationStatus: '调解失败',
        repaymentStatus: '未还款',
    },
    {
        caseId: '12',
        appointmentTime: '2025-01-14 14:00:00',
        mediationMethod: '线上调解',
        mediationStatus: '调解成功',
        repaymentStatus: '已还款',
    },
    {
        caseId: '19',
        appointmentTime: '2025-01-15 08:45:00',
        mediationMethod: '线上调解',
        mediationStatus: '调解成功',
        repaymentStatus: '已还款',
    },
])
const respondentName = ref('张铁牛')
const respondentPhoneNumber = ref('13806543210')
const respondentIdNumber = ref('120101198902254678')

// 申请机构信息
const applicantInstitution = ref('科技创新基金管理中心')
const applicantPhoneNumber = ref('13908765432')

// 案件信息
const caseDescription = ref('涉嫌知识产权侵权')
const caseReason = ref('专利侵权')

// 申请机构代理人信息
const agentName = ref('李思思')
const agentPhoneNumber = ref('13712345678')
const agentIdNumber = ref('120101199307091234')
const documentType = ref('护照')
const documentNumber = ref('G123456789')
const totalAmountDue = ref('200000')
const selectedappointmentTime = ref('')
const selectedmediationStatus = ref('')
const dialogVisible = ref(false)
// 当前选中的行数据
const currentRow = ref(false)
const showpicture = ref(false)

const pageSize = ref(5)
const currentPage = ref(1)
const total = ref(5)
const case1Options = computed(() => {
    const uniqueNames = new Set(
        tableData.value.map((item) => item.appointmentTime)
    )

    return Array.from(uniqueNames)
        .map((name) => ({
            value: name,
            label: name,
        }))
        .concat({
            value: '', // 设为一个空字符串表示“全部”
            label: '调解时间', // 显示为“全部”
        })
})
const case3Options = computed(() => {
    const uniqueStatus = new Set(
        tableData.value.map((item) => item.mediationStatus)
    )

    return Array.from(uniqueStatus)
        .map((name) => ({
            value: name,
            label: name,
        }))
        .concat({
            value: '', // 设为一个空字符串表示“全部”
            label: '调解结果', // 显示为“全部”
        })
})

// 根据选择的案件名称过滤数据
const filtered1Data = computed(() => {
    const selectedTime = selectedappointmentTime.value
    const selectedStatus = selectedmediationStatus.value
    if (
        (!selectedTime || selectedTime === '') &&
        (!selectedStatus || selectedStatus === '')
    ) {
        return tableData.value
    }

    const filtered = tableData.value.filter((item) => {
        const matchTime = selectedTime
            ? item.mediationTime === selectedTime
            : true
        const matchStatus = selectedStatus
            ? item.mediationStatus === selectedStatus
            : true
        return matchTime && matchStatus
    })

    console.log('Filtered Data:', filtered) // 输出过滤后的结果
    return filtered // 返回过滤后的数据
})
const viewpicture = (row) => {
    currentRow.value = row
    showpicture.value = true
}

const fetchData = async () => {
    const statusMap = {
        0: '未申请',
        1: '已申请',
        2: '调解中',
        3: '调解成功',
        4: '调解失败',
    }
    const mediationStatusMap = {
        0: '待分配调解员',
        1: '已分配调解员',
        2: '预约时间',
        3: '调解完成',
    }
    let res = await axios.get(`/custom/logic/mediator/getCases?status=3`)
    if (res.data.code === 200) {
        let result = res.data.data.map((item) => {
            item.status = statusMap[item.status]
            item.mediationStatus = mediationStatusMap[item.mediationStatus]
            return item
        })
        tableData.value = result
    }
}
const detailed = ref({
    respondentName: '王舞',
    respondentPhoneNumber: '13100000003',
    respondentIdNumber: '294289198905123921',
    respondentInstructName: '超级事务所',
    respondentInstructPhonenumber: '13100000002',
    respondentInstructPeopleName: '刘铭',
    respondentInstructPeoplePhoneNumber: '13100000004',
    respondentInstructPeopleIdType: '身份证',
    respondentInstructPeopleIdNumber: '294289196705123921',
    caseDescribe: '与被申请人有经济纠纷',
    caseReason: '与被申请人有经济纠纷',
    totalAmount: 45700,
})
// 查看详情方法
const viewDetails = async (row) => {
    currentRow.value = row // 将当前行数据赋值给 currentRow
    dialogVisible.value = true // 打开 DialogBox
    await getDetail()
}
const deepMergeObjects = (a, b) => {
    let newone = {}
    Object.assign(newone, a)
    for (const key in a) {
        if (!a[key] && b[key]) {
            newone[key] = b[key]
        }
    }
    for (const key in b) {
        if (!newone[key]) {
            newone[key] = b[key]
        }
    }
    return newone
}
const getDetail = async () => {
    try {
        let res = await axios.get(
            `/custom/logic/cases/getCases?caseId=${currentRow.value.caseId}`
        )
        if (res.status === 200) {
            if (res.data.code === 200) {
                detailed.value = deepMergeObjects(res.data.data, detailed.value)
                return
            }
        }
    } catch (error) {
        Modal.message('获取详情失败')
    }
}

onMounted(() => {
    fetchData()
})
</script>
<style scoped></style>
