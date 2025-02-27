<template>
    <div class="w-230 h-118.5 pos-relative top-40 left-50"
        ><tiny-grid
            :data="filtered1Data"
            :highlight-hover-row="true"
            :stripe="true"
            :edit-config="{ trigger: 'click', mode: 'cell', showStatus: true }"
            class="w-100% h-100% rounded-3xl"
        >
            <tiny-grid-column
                field="caseId"
                width="100"
                title="调解编号"
            ></tiny-grid-column>
            <tiny-grid-column
                field="mediatorId"
                width="150"
                title="调解员ID"
            ></tiny-grid-column>
            <tiny-grid-column
                field="caseReason"
                title="案由"
                width="180"
            ></tiny-grid-column>
            <tiny-grid-column
                field="mediationStatus"
                title="调解状态"
                width="200"
            ></tiny-grid-column>
            <tiny-grid-column
                field="createdAt"
                title="创建时间"
                width="200"
            ></tiny-grid-column>
            <tiny-grid-column title="详情" width="50">
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
            class="pos-absolute top-171 left-205"
        ></tiny-pager>
        <div class="pos-absolute top-43 left-117"
            ><tiny-base-select
                v-model="selectedmediatorName"
                class="w-20%"
                :searchable="true"
                style="width: 110px"
                placeholder="调解员ID"
                placement="top"
            >
                <tiny-option
                    v-for="(mediatorName, index) in case1Options"
                    :key="mediatorName.value"
                    :label="mediatorName.label"
                    :value="mediatorName.value"
                    :icon="mediatorName.icon"
                >
                </tiny-option></tiny-base-select
        ></div>
        <div class="pos-absolute top-43 left-148"
            ><tiny-base-select
                v-model="selectedmediationMethod"
                class="w-20%"
                :searchable="true"
                style="width: 110px"
                placeholder="调解方式"
                placement="top"
            >
                <tiny-option
                    v-for="(mediationMethod, index) in case2Options"
                    :key="mediationMethod.value"
                    :label="mediationMethod.label"
                    :value="mediationMethod.value"
                    :icon="mediationMethod.icon"
                >
                </tiny-option></tiny-base-select
        ></div>
        <div class="pos-absolute top-43 left-179"
            ><tiny-base-select
                v-model="selectedrepaymentStatus"
                class="w-20%"
                :searchable="true"
                style="width: 110px"
                placeholder="还款情况"
                placement="top"
            >
                <tiny-option
                    v-for="(repaymentStatus, index) in case3Options"
                    :key="repaymentStatus.value"
                    :label="repaymentStatus.label"
                    :value="repaymentStatus.value"
                    :icon="repaymentStatus.icon"
                >
                </tiny-option></tiny-base-select
        ></div>
        <div class="pos-absolute top-43 left-210"
            ><tiny-base-select
                v-model="selectedmediationStatus"
                class="w-20%"
                :searchable="true"
                style="width: 110px"
                placeholder="调解状态"
                placement="top"
            >
                <tiny-option
                    v-for="(mediationStatus, index) in case4Options"
                    :key="mediationStatus.value"
                    :label="mediationStatus.label"
                    :value="mediationStatus.value"
                    :icon="mediationStatus.icon"
                >
                </tiny-option></tiny-base-select
        ></div>
        <div class="pos-absolute top-43 left-241"
            ><tiny-base-select
                v-model="selectedcreatedAt"
                class="w-20%"
                :searchable="true"
                style="width: 110px"
                placeholder="创建时间"
                placement="top"
            >
                <tiny-option
                    v-for="(createdAt, index) in case5Options"
                    :key="createdAt.value"
                    :label="createdAt.label"
                    :value="createdAt.value"
                    :icon="createdAt.icon"
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
import { computed, onMounted, ref, watch } from 'vue'
import ProfileSection from '../profile/ProfileSection.vue'
import ProfileField from '../profile/ProfileField.vue'
import axios from 'axios'

const createData = ref({
    respondentName: '',
    respondentIdNumber: '',
    applicantName: '',
    caseReason: '',
    totalAmount: '',
    principalAmount: '',
    interestAmount: '',
    penaltyAmount: '',
    overdueInterest: '',
    mediationMethod: '', // 调解方式
    applicantFinancialInstitution: '', // 申请人所属金融机构
    mediatorName: '', // 调解员
    repaymentStatus: '', // 还款情况
})

const tableData = ref([])
const selectedmediatorName = ref('')
const selectedmediationMethod = ref('')
const selectedrepaymentStatus = ref('')
const selectedmediationStatus = ref('')
const selectedcreatedAt = ref('')

const dialogVisible = ref(false)
// 当前选中的行数据
const currentRow = ref(false)

const pageSize = ref(5)
const currentPage = ref(1)
const total = ref(100)
const case1Options = computed(() => {
    const uniqueNames = new Set(
        tableData.value.map((item) => item.mediatorName)
    )

    return Array.from(uniqueNames)
        .map((name) => ({
            value: name,
            label: name,
        }))
        .concat({
            value: '', // 设为一个空字符串表示“全部”
            label: '调解员', // 显示为“全部”
        })
})
const case2Options = computed(() => {
    const uniqueMethod = new Set(
        tableData.value.map((item) => item.mediationMethod)
    )

    return Array.from(uniqueMethod)
        .map((name) => ({
            value: name,
            label: name,
        }))
        .concat({
            value: '', // 设为一个空字符串表示“全部”
            label: '调解方式', // 显示为“全部”
        })
})
const case3Options = computed(() => {
    const uniqueMethod = new Set(
        tableData.value.map((item) => item.repaymentStatus)
    )

    return Array.from(uniqueMethod)
        .map((name) => ({
            value: name,
            label: name,
        }))
        .concat({
            value: '', // 设为一个空字符串表示“全部”
            label: '还款状态', // 显示为“全部”
        })
})
const case4Options = computed(() => {
    const uniqueMethod = new Set(
        tableData.value.map((item) => item.mediationStatus)
    )

    return Array.from(uniqueMethod)
        .map((name) => ({
            value: name,
            label: name,
        }))
        .concat({
            value: '', // 设为一个空字符串表示“全部”
            label: '调解状态', // 显示为“全部”
        })
})
const case5Options = computed(() => {
    const uniqueMethod = new Set(tableData.value.map((item) => item.createdAt))

    return Array.from(uniqueMethod)
        .map((name) => ({
            value: name,
            label: name,
        }))
        .concat({
            value: '', // 设为一个空字符串表示“全部”
            label: '创建时间', // 显示为“全部”
        })
})
// 根据选择的案件名称过滤数据
const filtered1Data = computed(() => {
    const selectedName = selectedmediatorName.value
    const selectedMethod = selectedmediationMethod.value
    const selectedStatus = selectedmediationStatus.value
    const selectedRepayment = selectedrepaymentStatus.value
    const selectedAt = selectedcreatedAt.value
    if (
        (!selectedName || selectedName === '') &&
        (!selectedMethod || selectedMethod === '') &&
        (!selectedStatus || selectedStatus === '') &&
        (!selectedRepayment || selectedRepayment === '') &&
        (!selectedAt || selectedAt === '')
    ) {
        return tableData.value // 返回所有数据
    }

    // 进行过滤操作
    const filtered = tableData.value.filter((item) => {
        const matchName = selectedName
            ? item.mediatorName === selectedName
            : true
        const matchMethod = selectedMethod
            ? item.mediationMethod === selectedMethod
            : true
        const matchmediationStatus = selectedStatus
            ? item.mediationStatus === selectedStatus
            : true
        const matchrepaymentStatus = selectedRepayment
            ? item.repaymentStatus === selectedRepayment
            : true
        const matchcreatedAt = selectedAt ? item.createdAt === selectedAt : true
        return (
            matchMethod &&
            matchName &&
            matchmediationStatus &&
            matchrepaymentStatus &&
            matchcreatedAt
        )
    })

    console.log('Filtered Data:', filtered) // 输出过滤后的结果
    return filtered // 返回过滤后的数据
})

const mediationStatusMap = {
    0: '待分配调解员',
    1: '已分配调解员',
    2: '预约时间',
    3: '调解完成',
}

const statusMap = {
    0: '未申请',
    1: '已申请',
    2: '调解中',
    3: '调解成功',
    4: '调解失败',
}

watch(currentPage, (newPage) => {
    // console.log(newPage)
    fetchPageData(newPage)
})

// 模拟获取分页数据
const fetchPageData = async (page) => {
    console.log('获取第', page, '页的数据')
    try {
        let res1 = await axios.post(
            '/custom/logic/administrator/caseConditionSelect',
            {
                mediatorStatus: '1',
                current: page,
                size: 5,
            }
        )
        let res2 = await axios.post(
            '/custom/logic/administrator/caseConditionSelect',
            {
                mediatorStatus: '2',
                current: page,
                size: 5,
            }
        )
        if (res1.data.code === 200 && res2.data.code === 200) {
            if (res1.data.data && res2.data.data) {
                console.log('Select success')
                let result = handleData(
                    res1.data.records.concat(res2.data.records)
                )
                tableData.value = result
            }
        }
    } catch (err) {
        Modal.message('连接超时')
    }
}

// async function adminCaseSelect() {
//     let res1 = await axios.post(
//         '/custom/logic/administrator/caseConditionSelect',
//         { mediatorStatus: '1' }
//     )
//     let res2 = await axios.post(
//         '/custom/logic/administrator/caseConditionSelect',
//         { mediatorStatus: '2' }
//     )
//     let result = []
//     if (res1.data.code === 200 && res2.data.code === 200) {
//         if (res1.data.data && res2.data.data) {
//             console.log('Select success')
//             result = res1.data.data.concat(res2.data.data).map((item) => {
//                 item.mediationStatus = mediationStatusMap[item.mediationStatus]
//                 return item
//             })
//             //resolve mediator name TODO
//             new Set(result.map((item) => item.mediatorId)).forEach((id) => {
//                 axios
//                     .post('/custom/logic/administrator/selectMediator', {})
//                     .then((res) => {
//                         if (res.data.code === 200) {
//                             if (res.data.data) {
//                                 result = result.map((item) => {
//                                     if (item.mediatorId === id) {
//                                         item.mediatorName = res.data.data.name
//                                     }
//                                     return item
//                                 })
//                             } else {
//                                 console.log('Get mediator name fail')
//                             }
//                         } else {
//                             console.log('Get mediator name fail')
//                         }
//                     })
//             })

//             tableData.value = result
//         } else {
//             console.log('Select fail')
//         }
//     }
//     console.log(res1)
// }

const handleData = (data) => {
    return data.map((item) => {
        item.mediationStatus = mediationStatusMap[item.mediationStatus]
        item.status = statusMap[item.status]
        return item
    })
}

const initData = async () => {
    let timeout = setTimeout(() => {
        console.log('timeout')
    }, 3000)
    try {
        let res1 = await axios.post(
            '/custom/logic/administrator/caseConditionSelect',
            {
                mediatorStatus: '1',
                current: 1,
                size: 5,
            }
        )
        let res2 = await axios.post(
            '/custom/logic/administrator/caseConditionSelect',
            {
                mediatorStatus: '2',
                current: 1,
                size: 5,
            }
        )
        if (res1.data.code === 200 && res2.data.code === 200) {
            clearTimeout(timeout)
            if (res1.data.data && res2.data.data) {
                console.log('Select success')
                pageSize.value = res1.data.data.size
                total.value = res1.data.data.total
                let result = handleData(
                    res1.data.data.records.concat(res2.data.data.records)
                )
                tableData.value = result
            }
        }
    } catch {
        Modal.message('连接超时')
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
onMounted(async () => {
    await initData()
})
</script>
<style scoped></style>
