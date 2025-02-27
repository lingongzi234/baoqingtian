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
                width="100"
                title="调解编号"
            ></tiny-grid-column>
            <tiny-grid-column field="mediatorName" width="150" title="调解员">
                <template #default="{ row }">
                    <div class="flex items-center justify-start">
                        <div class="w-20 items-center justify-start">{{
                            row.mediatorName
                        }}</div>
                        <button
                            v-if="row.mediatorId === 0"
                            @click="getMediator(row)"
                            class="w-15 h-15 hover-bg-blue"
                            >分配</button
                        >
                    </div>
                </template>
            </tiny-grid-column>
            <tiny-grid-column
                field="caseReason"
                title="案由"
                width="200"
            ></tiny-grid-column>
            <tiny-grid-column
                field="createdAt"
                title="创建时间"
                width="200"
            ></tiny-grid-column>
            <tiny-grid-column
                field="status"
                title="案件状态"
                width="120"
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

        <div class="pos-absolute top-43 left-119"
            ><tiny-base-select
                v-model="selectedcaseReason"
                class="w-20%"
                :searchable="true"
                style="width: 110px"
                placeholder="案由"
            >
                <tiny-option
                    v-for="(caseReason, index) in case3Options"
                    :key="caseReason.value"
                    :label="caseReason.label"
                    :value="caseReason.value"
                    :icon="caseReason.icon"
                >
                </tiny-option></tiny-base-select
        ></div>
        <div class="pos-absolute top-43 left-150"
            ><tiny-base-select
                v-model="selectedstatus"
                class="w-20%"
                :searchable="true"
                style="width: 110px"
                placeholder="案件状态"
            >
                <tiny-option
                    v-for="(status, index) in case4Options"
                    :key="status.value"
                    :label="status.label"
                    :value="status.value"
                    :icon="status.icon"
                >
                </tiny-option></tiny-base-select
        ></div>
        <div class="pos-absolute top-43 left-181"
            ><tiny-base-select
                v-model="selectedcreatedAt"
                class="w-20%"
                :searchable="true"
                style="width: 110px"
                placeholder="创建时间"
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
        v-model:visible="getapplication"
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
        <template #footer>
            <tiny-button type="primary" @click="getapplication = false">
                确定
            </tiny-button>
            <tiny-button @click="getapplication = false"> 取消 </tiny-button>
        </template>
    </tiny-dialog-box>
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
        v-model:visible="choosedialogVisible"
        title="选择调解员"
        width="50%"
        main-height="100%"
    >
        <template #default>
            <div class="flex justify-start gap-0">
                <div class="w-60"></div>
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
                <div></div>
            </div>
        </template>
        <template #footer>
            <tiny-button type="primary" @click="uploadMediator">
                确定
            </tiny-button>
            <tiny-button @click="choosedialogVisible = false">
                取消
            </tiny-button>
        </template>
    </tiny-dialog-box>
</template>
<script setup>
import {
    TinyBaseSelect,
    TinyButton,
    TinyDialogBox,
    TinyDivider,
    TinyGrid,
    TinyGridColumn,
    TinyInput,
    TinyOption,
    TinyPager,
    Modal,
} from '@opentiny/vue'
import { computed, ref, onMounted, watch } from 'vue'
import ProfileSection from '../profile/ProfileSection.vue'
import ProfileField from '../profile/ProfileField.vue'
import axios from 'axios'
const tableData = ref([
    {
        caseId: '1',
        mediatorName: '李勇',
        mediationMethod: '仲裁',
        applicantFinancialInstitution: '超级事务所',
        createdAt: '2024-06-13',
        Status: '待调解',
    },
])
const selectedcaseReason = ref('')
const selectedstatus = ref('')
const selectedcreatedAt = ref('')
const dialogVisible = ref(false)
const choosedialogVisible = ref(false)
const getapplication = ref(false)
// 当前选中的行数据
const currentRow = ref(false)
const allmediator = ref([])
const mediatorSelect = ref('')
const pageSize = ref(5)
const currentPage = ref(1)
const total = ref(100)
const getMediator = async (row) => {
    currentRow.value = row
    choosedialogVisible.value = true
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
const getApplication = (row) => {
    currentRow.value = row
    getapplication.value = true
}
const case3Options = computed(() => {
    const uniqueFinacial = new Set(
        tableData.value.map((item) => item.caseReason)
    )

    return Array.from(uniqueFinacial)
        .map((name) => ({
            value: name,
            label: name,
        }))
        .concat({
            value: '', // 设为一个空字符串表示“全部”
            label: '案由', // 显示为“全部”
        })
})
const case4Options = computed(() => {
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
const case5Options = computed(() => {
    const uniquecreatedAt = new Set(
        tableData.value.map((item) => item.createdAt)
    )

    return Array.from(uniquecreatedAt)
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
    const selectedbyStatus = selectedstatus.value
    const selectedReason = selectedcaseReason.value
    const selectedAt = selectedcreatedAt.value
    if (
        (!selectedbyStatus || selectedbyStatus === '') &&
        (!selectedAt || selectedAt === '') &&
        (!selectedReason || selectedReason === '')
    ) {
        return tableData.value
    }
    const filtered = tableData.value.filter((item) => {
        const matchStatus = selectedbyStatus
            ? item.status === selectedbyStatus
            : true
        const matchapplicantFinancialInstitution = selectedReason
            ? item.caseReason === selectedReason
            : true
        const matchcreatedAt = selectedAt ? item.createdAt === selectedAt : true
        return (
            matchStatus && matchapplicantFinancialInstitution && matchcreatedAt
        )
    })
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

const handleData = (data) => {
    return data.map((item) => {
        item.mediationStatus = mediationStatusMap[item.mediationStatus]
        item.status = statusMap[item.status]
        return item
    })
}

const initData = async () => {
    try {
        let res1 = await axios.post(
            '/custom/logic/administrator/caseConditionSelect',
            {
                mediatorStatus: '0',
                current: 1,
                size: 5,
            }
        )
        if (res1.data.code === 200) {
            console.log('Select success')
            console.log(res1.data)
            pageSize.value = res1.data.data.size
            total.value = res1.data.data.total
            let result = handleData(res1.data.data.records)
            tableData.value = result
        }
    } catch (error) {
        Modal.message('获取数据失败')
    }
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
                mediatorStatus: '0',
                current: page,
                size: 5,
            }
        )
        if (res1.data.code === 200) {
            if (res1.data.data) {
                console.log('Select success')
                let result = handleData(res1.data.data.records)
                tableData.value = result
            }
        }
    } catch (err) {
        Modal.message('获取数据失败')
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
    Modal.message('获取详情')
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
