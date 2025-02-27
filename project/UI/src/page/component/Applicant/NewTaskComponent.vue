<template>
    <div class="bg-#E6F2FF w-screen h-screen">
        <div
            class="80% w-50% bg-#E6F2FF rounded-3xl pos-relative left-50 top-30"
        >
            <div class="w-230 h-118.5 pos-absolute top-10"
                ><tiny-grid
                    :data="filtered1Data"
                    :highlight-hover-row="true"
                    :stripe="true"
                    :edit-config="{
                        trigger: 'click',
                        mode: 'cell',
                        showStatus: true,
                    }"
                    class="w-100% h-100% rounded-3xl"
                >
                    <tiny-grid-column
                        field="caseId"
                        width="100"
                        title="调解编号"
                    ></tiny-grid-column>
                    <tiny-grid-column
                        field="caseReason"
                        width="150"
                        title="案件原因"
                    ></tiny-grid-column>
                    <tiny-grid-column
                        field="respondentName"
                        title="被申请人"
                        width="100"
                        title-align="center"
                    ></tiny-grid-column>
                    <tiny-grid-column
                        field="status"
                        title="案件状态"
                        width="300"
                        ><template #default="{ row }">
                            <div class="flex items-center justify-start">
                                <div class="w-20 items-center justify-start">{{
                                    row.status
                                }}</div>
                                <button
                                    v-if="row.status === '未申请'"
                                    @click="changeTask(row)"
                                    class="w-15 h-15 hover-bg-blue"
                                    >编辑</button
                                >
                                <button
                                    v-if="row.status === '未申请'"
                                    @click="deleteTask(row)"
                                    class="w-15 h-15 hover-bg-blue"
                                    >删除</button
                                >
                                <button
                                    v-if="row.status === '未申请'"
                                    @click="updateTask(row)"
                                    class="w-15 h-15 hover-bg-blue"
                                    >申请</button
                                >
                            </div>
                        </template></tiny-grid-column
                    >
                    <tiny-grid-column
                        field="mediationStatus"
                        title="申请状态"
                        width="170"
                    ></tiny-grid-column>
                    <tiny-grid-column title="操作" width="50">
                        <template #default="{ row }">
                            <!-- 详情按钮 -->
                            <button
                                v-if="row.respondentName !== 'thisName'"
                                @click="viewDetails(row)"
                                class="w-15 h-15 hover-bg-blue"
                                >详情</button
                            >
                            <button
                                v-if="
                                    row.respondentName === '王舞' &&
                                    userId === '38'
                                "
                                @click="getSolution(row)"
                                class="w-15 h-15 hover-bg-blue"
                                >方案</button
                            >
                        </template>
                    </tiny-grid-column>
                </tiny-grid>
            </div>
        </div>
        <div class="w-100% h-30%">
            <tiny-pager
                mode="number"
                layout="prev, pager, next, slot"
                :page-size="pageSize"
                :page-sizes="[5, 7, 10, 20, 50]"
                :total="total"
                v-model:current-page="currentPage"
                class="pos-absolute top-168 left-177"
            >
            </tiny-pager>
            <div class="pos-absolute top-30 left-120"
                ><tiny-base-select
                    v-model="selectedcaseReason"
                    class="w-20%"
                    :searchable="true"
                    style="width: 120px"
                    placeholder="案件由"
                    placement="top"
                >
                    <tiny-option
                        v-for="(caseReason, index) in case1Options"
                        :key="caseReason.value"
                        :label="caseReason.label"
                        :value="caseReason.value"
                        :icon="caseReason.icon"
                    >
                    </tiny-option></tiny-base-select
            ></div>
            <div class="pos-absolute top-30 left-155"
                ><tiny-base-select
                    v-model="selectedrespondentName"
                    class="w-20%"
                    :searchable="true"
                    style="width: 120px"
                    placeholder="被申请人"
                    placement="top"
                >
                    <tiny-option
                        v-for="(respondentName, index) in case2Options"
                        :key="respondentName.value"
                        :label="respondentName.label"
                        :value="respondentName.value"
                        :icon="respondentName.icon"
                    >
                    </tiny-option></tiny-base-select
            ></div>
            <div class="pos-absolute top-30 left-190"
                ><tiny-base-select
                    v-model="selectedstatus"
                    class="w-20%"
                    :searchable="true"
                    style="width: 120px"
                    placeholder="案件状态"
                    placement="top"
                >
                    <tiny-option
                        v-for="(status, index) in case3Options"
                        :key="status.value"
                        :label="status.label"
                        :value="status.value"
                        :icon="status.icon"
                    >
                    </tiny-option></tiny-base-select
            ></div>
            <div class="pos-absolute top-30 left-225"
                ><tiny-base-select
                    v-model="selectedmediationStatus"
                    class="w-20%"
                    :searchable="true"
                    style="width: 120px"
                    placeholder="申请状态"
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
        </div>
        <tiny-button
            @click="boxVisibility = true"
            title="新增案件"
            class="pos-absolute top-170 left-290"
            >新增案件</tiny-button
        >
        <tiny-dialog-box
            v-model:visible="boxVisibility"
            title="新增案件"
            width="60%"
            height="100%"
        >
            <div class="flex"
                ><tiny-form
                    class="w-50% h-10%"
                    ref="ruleFormRef"
                    :model="createData"
                    label-width="150px"
                    label-position="left"
                >
                    <tiny-form-item
                        label="被申请人姓名 "
                        prop="respondentName"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.respondentName"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="联系方式"
                        prop="respondentPhoneNumber"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.respondentPhoneNumber"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="被申请人身份证号"
                        prop="respondentIdNumber"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.respondentIdNumber"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="申请机构名称"
                        prop="totalAmount"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.respondentInstructName"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="联系方式"
                        prop="respodentnation"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.respondentInstructPhonenumber"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>

                    <tiny-form-item
                        label="本金"
                        prop="principalAmount"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.principalAmount"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="案由"
                        prop="caseReason"
                        class="w-30%"
                    >
                        <tiny-input
                            v-model="createData.caseReason"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                </tiny-form>
                <tiny-form
                    class="w-50% h-10%"
                    ref="ruleFormRef"
                    :model="createData"
                    label-width="150px"
                    label-position="left"
                >
                    <tiny-form-item
                        label="申请机构代理人名称"
                        prop="respondenteducation"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.respondentInstructPeopleName"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="联系方式"
                        prop="respondentaddress"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="
                                createData.respondentInstructPeoplePhoneNumber
                            "
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="证件类型"
                        prop="respondentborndate"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.respondentInstructPeopleIdType"
                            style="width: 200px"
                        ></tiny-input
                    ></tiny-form-item>
                    <tiny-form-item
                        label="证件号码"
                        prop="respondentborndate"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="
                                createData.respondentInstructPeopleIdNumber
                            "
                            style="width: 200px"
                        ></tiny-input
                    ></tiny-form-item>

                    <tiny-form-item
                        label="案件描述"
                        prop="caseReason"
                        class="w-30%"
                    >
                        <tiny-input
                            v-model="createData.caseDescribe"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="利息金额"
                        prop="interestAmount"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.interestAmount"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="罚款金额"
                        prop="penaltyAmount"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.penaltyAmount"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="逾期利息"
                        prop="overdueInterest"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.overdueInterest"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                </tiny-form>
            </div>

            <template #footer>
                <tiny-button type="primary" @click="onSubmit">
                    确定
                </tiny-button>
                <tiny-button @click="boxVisibility = false"> 取消 </tiny-button>
            </template>
        </tiny-dialog-box>
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
        v-model:visible="solutionVisibility"
        title="选择案件"
        width="60%"
        height="100%"
    >
        <div class="flex">
            <div class="w-30%">方案：</div>
            <div class="w-70%">{{ solution }}</div>
        </div>

        <template #footer>
            <tiny-button type="primary" @click="nextsolution">
                下一个
            </tiny-button>
            <tiny-button type="primary" @click="chooseSolution">
                选择
            </tiny-button>
            <tiny-button @click="solutionVisibility = false">
                取消
            </tiny-button>
        </template>
    </tiny-dialog-box>
</template>
<script setup>
import {
    TinyGrid,
    TinyGridColumn,
    TinyBaseSelect,
    TinyOption,
    TinyButton,
    TinyDialogBox,
    TinyDivider,
    TinyInput,
    TinyFormItem,
    Modal,
    TinyPopUpload,
} from '@opentiny/vue'
import { TinyPager, TinyModal, TinyTextPopup, TinyForm } from '@opentiny/vue'
import { computed, reactive, ref, renderSlot, watch, onMounted } from 'vue'
import axios from 'axios'
import ProfileSection from '../profile/ProfileSection.vue'
import ProfileField from '../profile/ProfileField.vue'

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
    respondentPhoneNumber: '',
    respondentInstructName: '',
    respondentInstructPhonenumber: '',
    respondentInstructPeopleName: '',
    respondentInstructPeoplePhoneNumber: '',
    respondentInstructPeopleIdType: '',
    respondentInstructPeopleIdNumber: '',
    caseDescribe: '',
})

const userId = localStorage.getItem('userid')

const tableData = ref([])
const pageSize = ref(5)
const currentPage = ref(1)
const total = ref(10)
const solution = ref('')
const currentSolutionSelect = ref(0)
const plans = ref([])

const dialogVisible = ref(false)
// 当前选中的行数据
const currentRow = ref(false)

const changeTask = (row) => {
    currentRow.value = row
    boxVisibility.value = true
}
const deleteTask = (row) => {
    currentRow.value = row
}

const requestSolution = async (caseid) => {
    let res = await axios.get(`/ls/logic/respondent/plan?case_id=${caseid}`)
    if (res.status === 200) {
        if (res.data.code === 200) {
            return res.data.data
        }
    }
    return '请求错误'
}
const getSolution = async (row) => {
    currentRow.value = row
    solutionVisibility.value = true
    plans.value = await requestSolution(row.caseId)
    solution.value = plans.value[0].planDetails
}

const nextsolution = async () => {
    if (currentSolutionSelect.value < plans.value.length - 1) {
        currentSolutionSelect.value++
        solution.value = plans.value[currentSolutionSelect.value].planDetails
    }
}

const chooseSolution = async () => {
    let res = await axios.post('/ls/logic/respondent/choose', {
        case_id: currentRow.value.caseId,
        times: currentSolutionSelect.value,
    })
    if (res.status === 200) {
        if (res.data.code === 200) {
            Modal.message('选择方案成功')
            return
        }
    }
    Modal.message('选择方案失败')
}

const updateTask = async (row) => {
    currentRow.value = row
    let data = currentRow.value
    delete data._RID
    delete data.status
    delete data.mediationStatus
    let res = await axios.post('/custom/logic/cases/updateCase', data)
    if (res.status === 200) {
        if (res.data.code === 200) {
            Modal.message('更新案件成功')
            return
        }
    }
}
const boxVisibility = ref(false)
const solutionVisibility = ref(false)
const selectedcaseReason = ref('')
const selectedrespondentName = ref('')
const selectedstatus = ref('')
const selectedmediationStatus = ref('')

const case1Options = computed(() => {
    if (typeof tableData.value === 'undefined') {
        return []
    }
    const uniqueReason = new Set(tableData.value.map((item) => item.caseReason))

    return Array.from(uniqueReason)
        .map((name) => ({
            value: name,
            label: name,
        }))
        .concat({
            value: '', // 设为一个空字符串表示“全部”
            label: '案件原因', // 显示为“全部”
        })
})
const case2Options = computed(() => {
    if (typeof tableData.value === 'undefined') {
        return []
    }
    const uniqueName = new Set(
        tableData.value.map((item) => item.respondentName)
    )

    return Array.from(uniqueName)
        .map((name) => ({
            value: name,
            label: name,
        }))
        .concat({
            value: '', // 设为一个空字符串表示“全部”
            label: '被申请人', // 显示为“全部”
        })
})
const case3Options = computed(() => {
    if (typeof tableData.value === 'undefined') {
        return []
    }
    const uniquestatus = new Set(tableData.value.map((item) => item.status))

    return Array.from(uniquestatus)
        .map((name) => ({
            value: name,
            label: name,
        }))
        .concat({
            value: '', // 设为一个空字符串表示“全部”
            label: '案件状态', // 显示为“全部”
        })
})
const case4Options = computed(() => {
    if (typeof tableData.value === 'undefined') {
        return []
    }
    const uniquemediation = new Set(
        tableData.value.map((item) => item.mediationStatus)
    )

    return Array.from(uniquemediation)
        .map((name) => ({
            value: name,
            label: name,
        }))
        .concat({
            value: '', // 设为一个空字符串表示“全部”
            label: '申请状态', // 显示为“全部”
        })
})

const filtered1Data = computed(() => {
    const selectedReason = selectedcaseReason.value
    const selectedName = selectedrespondentName.value
    const selectedbystatus = selectedstatus.value
    const selectedmediation = selectedmediationStatus.value
    if (
        (!selectedReason || selectedReason === '') &&
        (!selectedName || selectedName === '') &&
        (!selectedbystatus || selectedbystatus === '') &&
        (!selectedmediation || selectedmediation === '')
    ) {
        return tableData.value // 返回所有数据
    }

    // 进行过滤操作
    const filtered = tableData.value.filter((item) => {
        const matchReason = selectedReason
            ? item.caseReason === selectedReason
            : true
        const matchName = selectedName
            ? item.respondentName === selectedName
            : true
        const matchstatus = selectedbystatus
            ? item.status === selectedbystatus
            : true
        const matchmediationStatus = selectedmediation
            ? item.mediationStatus === selectedmediation
            : true

        return matchReason && matchName && matchmediationStatus && matchstatus
    })

    console.log('Filtered Data:', filtered) // 输出过滤后的结果
    return filtered // 返回过滤后的数据
})

const onSubmit = async () => {
    createData.value.totalAmount =
        parseInt(createData.value.interestAmount) +
        parseInt(createData.value.principalAmount) +
        parseInt(createData.value.penaltyAmount) +
        parseInt(createData.value.overdueInterest)
    createData.value.applicantName = '暂无'
    const data = JSON.stringify(createData.value)

    let res = await axios.post('/custom/logic/cases/addCase', JSON.parse(data))
    if (res.status === 200) {
        if (res.data.code === 200) {
            Modal.message('新增案件成功')
            return
        }
    }

    Modal.message('新增案件失败')
}
watch(currentPage, (newPage) => {
    // console.log(newPage)
    fetchPageData(newPage)
})

// 模拟获取分页数据
const fetchPageData = async (page) => {
    console.log('获取第', page, '页的数据')
    // 在这里发起 API 请求，或更新表格数据
    try {
        let res = await axios.post(`/custom/logic/cases/conditionSelect`, {
            current: page,
            size: 5,
        })
        handleData(res)
    } catch (error) {
        Modal.message('获取案件失败')
    }
}
// const fetchData = async () => {
//     const statusMap = {
//         0: '未申请',
//         1: '已申请',
//         2: '调解中',
//         3: '调解成功',
//         4: '调解失败',
//     }
//     const mediationStatusMap = {
//         0: '待分配调解员',
//         1: '已分配调解员',
//         2: '预约时间',
//         3: '调解完成',
//         4: '调解失败',
//     }
//     let applicantRes = await axios.post('/custom/logic/cases/conditionSelect', {
//         caseReason: '',
//         mediatorStatus: '',
//         respondentIdNumber: '',
//         status: '',
//     })
//     let respondentRes = await axios.get(
//         `/ls/logic/respondent/case?id=${localStorage.getItem('userid')}`
//     )
//     if (applicantRes.status === 200 && respondentRes.status === 200) {
//         if (applicantRes.data.code === 200 && respondentRes.data.code === 200) {
//             const result = applicantRes.data.data
//                 .concat(respondentRes.data.data)
//                 .map((item) => {
//                     item.mediationStatus =
//                         mediationStatusMap[item.mediationStatus]
//                     item.status = statusMap[item.status]
//                     return item
//                 })
//             tableData.value = result
//             console.log(tableData.value)
//             return
//         } else {
//             Modal.message('获取案件失败')
//         }
//     } else {
//         Modal.message('获取案件失败')
//     }
// }

const handleData = (res) => {
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
        4: '调解失败',
    }
    if (res.status === 200) {
        if (res.data.code === 200) {
            pageSize.value = res.data.data.size
            total.value = res.data.data.total
            const result = res.data.data.records.map((item) => {
                item.mediationStatus = mediationStatusMap[item.mediationStatus]
                item.status = statusMap[item.status]
                return item
            })
            tableData.value = result
            return
        }
    }
}

const initData = async () => {
    try {
        let res = await axios.post(`/custom/logic/cases/conditionSelect`, {
            current: 0,
            size: 5,
        })
        handleData(res)
    } catch (error) {
        Modal.message('获取案件失败')
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
    initData()
})
</script>
<style scoped></style>
