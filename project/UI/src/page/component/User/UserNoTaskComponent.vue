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
                width="260"
                title="调解编号"
            ></tiny-grid-column>
            <tiny-grid-column
                field="appointmentTime"
                width="260"
                title="调解时间"
                ><template #default="{ row }">
                    <div class="flex items-center justify-start">
                        <div class="w-20 items-center justify-start">{{
                            row.appointmentTime
                        }}</div>
                        <button
                            @click="changeTime(row)"
                            class="w-15 h-15 hover-bg-blue"
                            >预约</button
                        >
                    </div>
                </template>
            </tiny-grid-column>
            <tiny-grid-column field="status" title="案件状态" width="260">
                <template #default="{ row }">
                    <div class="flex items-center justify-start">
                        <div class="w-20 items-center justify-start">{{
                            row.status
                        }}</div>
                        <button
                            v-if="row.status === '调解中'"
                            @click="changeTask(row)"
                            class="w-15 h-15 hover-bg-blue"
                            >转交</button
                        >
                        <button
                            v-if="row.status === '调解中'"
                            @click="useTask(row)"
                            class="w-15 h-15 hover-bg-blue"
                            >调解</button
                        >
                    </div>
                </template></tiny-grid-column
            >
            <tiny-grid-column title="操作" width="50">
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
            class="pos-absolute top-171 left-180"
        ></tiny-pager>
        <div class="pos-absolute top-43 left-117"
            ><tiny-base-select
                v-model="selectedmediationTime"
                class="w-20%"
                :searchable="true"
                style="width: 110px"
                placeholder="调解时间"
                placement="top"
            >
                <tiny-option
                    v-for="(mediationTime, index) in case1Options"
                    :key="mediationTime.value"
                    :label="mediationTime.label"
                    :value="mediationTime.value"
                    :icon="mediationTime.icon"
                >
                </tiny-option></tiny-base-select
        ></div>
        <div class="pos-absolute top-43 left-148"
            ><tiny-base-select
                v-model="selectedstatus"
                class="w-20%"
                :searchable="true"
                style="width: 110px"
                placeholder="调解状态"
                placement="top"
            >
                <tiny-option
                    v-for="(status, index) in case2Options"
                    :key="status.value"
                    :label="status.label"
                    :value="status.value"
                    :icon="status.icon"
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
        v-model:visible="changedialogVisible"
        title="转交案件"
        width="50%"
        main-height="100%"
    >
        <template #default>
            <div class="flex justify-start gap-0">
                <div class="w-60"></div>
                <div class="w-40 font-bold text-lg">被转交人:</div>
                <div>
                    <tiny-base-select
                        v-model="mediatorSelect"
                        class="w-20%"
                        :searchable="true"
                        style="width: 310px"
                        placeholder="调解员"
                    >
                        <tiny-option
                            v-for="mediatorName in allmediator"
                            :key="mediatorName.value"
                            :label="mediatorName.label"
                            :value="mediatorName.value"
                        >
                        </tiny-option
                    ></tiny-base-select>
                </div>
            </div>
        </template>
        <template #footer>
            <tiny-button type="primary" @click="uploadMediator">
                确定
            </tiny-button>
            <tiny-button @click="changedialogVisible = false">
                取消
            </tiny-button>
        </template>
    </tiny-dialog-box>
    <tiny-dialog-box
        v-model:visible="changedtime"
        title="预约时间"
        width="50%"
        main-height="100%"
    >
        <template #default>
            <div class="flex justify-start gap-0">
                <div class="w-60"></div>
                <div class="w-40 font-bold text-lg">预约时间:</div>
                <div
                    ><tiny-input
                        v-model="createData.mediationtime"
                        style="width: 200px"
                    ></tiny-input
                ></div>
            </div>
        </template>
        <template #footer>
            <tiny-button type="primary" @click="appointmentTime">
                确定
            </tiny-button>
            <tiny-button @click="changedtime = false"> 取消 </tiny-button>
        </template>
    </tiny-dialog-box>
    <tiny-dialog-box
        v-model:visible="usedialogVisible"
        title="制定方案"
        width="50%"
        main-height="100%"
    >
        <template #default>
            <div class="flex justify-start gap-0">
                <div class="w-30"></div>
                <div class="w-20 font-bold text-lg">调解方案:</div>
                <div
                    ><tiny-input
                        v-model="createData.mediationscheme"
                        style="width: 400px"
                        required
                    ></tiny-input
                ></div>
            </div>
        </template>
        <template #footer>
            <tiny-button type="primary" @click="dispatchPlan">
                确定
            </tiny-button>
            <tiny-button @click="usedialogVisible = false"> 取消 </tiny-button>
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
    TinyButton,
    TinyInput,
    TinyFormItem,
    TinyForm,
    Modal,
} from '@opentiny/vue'
import { computed, ref, onMounted } from 'vue'
import ProfileField from '../profile/ProfileField.vue'
import ProfileSection from '../profile/ProfileSection.vue'
import axios from 'axios'

const allmediator = ref([])

const respondentName = ref('王六六')
const respondentPhoneNumber = ref('13804260000')
const respondentIdNumber = ref('110101199001012345')

// 申请机构信息
const applicantInstitution = ref('经济发展局')
const applicantPhoneNumber = ref('13900000001')

// 案件信息
const caseDescription = ref('申请调解合同纠纷')
const caseReason = ref('合同违约')

// 申请机构代理人信息
const agentName = ref('周邮市解')
const agentPhoneNumber = ref('13708650002')
const agentIdNumber = ref('110101198802222233')
const documentType = ref('身份证')
const documentNumber = ref('110101198802222233')
const totalAmountDue = ref('50000')
const changedtime = ref(false)
const mediatorSelect = ref('')
const createData = ref([
    {
        mediatorId: '',
        mediationscheme: '',
    },
])
const tableData = ref([
    {
        caseId: '1',
        appointmentTime: '2025-01-12 09:00:00',
        mediationStatus: '未受理',
    },
    {
        caseId: '3',
        appointmentTime: '2025-01-13 10:30:00',
        mediationStatus: '未受理',
    },
    {
        caseId: '7',
        appointmentTime: '2025-01-14 14:00:00',
        mediationStatus: '等待调解',
    },
    {
        caseId: '10',
        appointmentTime: '2025-01-15 08:45:00',
        mediationStatus: '等待调解',
    },
])
const selectedmediationTime = ref('')
const selectedstatus = ref('')
const dialogVisible = ref(false)
const changedialogVisible = ref(false)
const usedialogVisible = ref(false)
// 当前选中的行数据
const currentRow = ref(false)

const pageSize = ref(5)
const currentPage = ref(1)
const total = ref(5)
// 查看详情方法
const changeTime = async (row) => {
    currentRow.value = row
    changedtime.value = true
}
const changeTask = async (row) => {
    currentRow.value = row
    changedialogVisible.value = true
    let res = await axios.post('/custom/logic/administrator/selectMediator', {})
    if (res.data.code === 200) {
        if (res.data.data) {
            console.log('Get mediator success')
            allmediator.value = res.data.data.map((item) => {
                return {
                    value: item.mediatorId,
                    label: item.name,
                }
            })
        } else {
            console.log('Get mediator fail')
        }
    } else {
        console.log('Get mediator fail')
    }
}
const useTask = (row) => {
    currentRow.value = row
    usedialogVisible.value = true
}
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
const case2Options = computed(() => {
    const uniqueStatus = new Set(tableData.value.map((item) => item.status))

    return Array.from(uniqueStatus)
        .map((name) => ({
            value: name,
            label: name,
        }))
        .concat({
            value: '', // 设为一个空字符串表示“全部”
            label: '案件状态', // 显示为“全部”
        })
})

// 根据选择的案件名称过滤数据
const filtered1Data = computed(() => {
    const selectedTime = selectedmediationTime.value
    const selectedStatus = selectedstatus.value
    if (
        (!selectedTime || selectedTime === '') &&
        (!selectedStatus || selectedStatus === '')
    ) {
        return tableData.value
    }

    const filtered = tableData.value.filter((item) => {
        const matchTime = selectedTime
            ? item.appointmentTime === selectedTime
            : true
        const matchStatus = selectedStatus
            ? item.status === selectedStatus
            : true
        return matchTime && matchStatus
    })
    // 输出过滤后的结果
    return filtered // 返回过滤后的数据
})

const fetchData = async () => {
    const statusMap = {
        0: '未申请',
        1: '已申请',
        2: '调解中',
        3: '调解成功',
        4: '调解失败',
    }
    try {
        let res = await axios.get(`/custom/logic/mediator/getCases?status=2`)
        if (res.data.code === 200) {
            let result = res.data.data.map((item) => {
                item.status = statusMap[item.status]
                return item
            })
            tableData.value = result
            console.log(tableData.value)
        }
    } catch {
        Modal.message('连接超时')
    }
}

const appointmentTime = async () => {
    try {
        const id = currentRow.value.respondentIdNumber
        let res = await axios.post(`/ls/logic/assistant/reserve/`, {
            id_card: id,
            case_id: currentRow.value.caseId,
            time: createData.value.mediationtime,
        })
        if (res.data.code === 200) {
            Modal.message('预约成功')
        }
    } catch (err) {
        Modal.message('连接超时')
        console.log(err)
    }
}

async function uploadMediator() {
    console.log(currentRow.value)
    const caseId = currentRow.value.caseId
    const mediatorId = mediatorSelect.value
    if (caseId && mediatorId) {
        let res = await axios.get(
            `/custom/logic/administrator/caseGetMediator?caseId=${caseId}&mediatorId=${mediatorId}`
        )
        if (res.data.code === 200) {
            Modal.message('分配调解员成功')
        } else {
            Modal.message('分配调解员失败')
        }
    } else {
        Modal.message('分配调解员失败')
    }
}

async function selectMediator() {
    let res = await axios.get('/custom/logic/administrator/selectMediator')
    if (res.data.code === 200) {
        if (res.data.data) {
            console.log('Get mediator success')
        } else {
            console.log('Get mediator fail')
        }
    } else {
        console.log('Get mediator fail')
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

const dispatchPlan = async () => {
    usedialogVisible.value = false
    const caseId = currentRow.value.caseId
    const planDetail = createData.value.mediationscheme
    try {
        let res = await axios.get(
            `/custom/logic/cases/caseAddPlan?caseId=${caseId}&planDetails=${planDetail}`
        )
        Modal.message('方案提交成功')
    } catch (err) {
        Modal.message('方案提交失败')
    }
}

onMounted(() => {
    fetchData()
})
</script>
<style scoped></style>
