<template>
    <div
        class="profile-card text-xl flex flex-col items-center p-6 bg-gray-100 rounded-xl pos-relative left-60 top-30 shadow-md w-200 h-130"
    >
        <div class="flex flex-row items-center justify-start gap-12">
            <ProfileSection>
                <ProfileField label="姓名" :value="userData.name" />
                <ProfileField label="性别" :value="userData.gender" />
                <ProfileField label="出生日期" :value="userData.borndate" />
                <ProfileField
                    label="身份证号"
                    :value="userData.identitynumber"
                />
                <ProfileField label="联系方式" :value="userData.phonenumber" />
            </ProfileSection>

            <tiny-divider
                class="divider"
                direction="vertical"
                :style="{ height: '300px', borderTopWidth: '5px' }"
            ></tiny-divider>
            <ProfileSection>
                <ProfileField label="学历" :value="userData.education" />
                <ProfileField label="电子邮箱" :value="userData.email" />
                <ProfileField label="从业年限" :value="userData.worktime" />
                <ProfileField label="擅长领域" :value="userData.goodarea" />
                <ProfileField label="工作单位" :value="userData.workarea" />
            </ProfileSection>
        </div>

        <div class="pl-10 pt-6">
            <ProfileSection>
                <ProfileField
                    label="工作经历"
                    :value="userData.workexperience"
                />
                <ProfileField label="资历和证书" :value="userData.experience" />
            </ProfileSection>
        </div>

        <el-button
            class="b-black w-32 h-7.5 mt-6 bg-gray rounded-lg hover-bg-blue hover:cursor-pointer"
            @click="handleChangeClick"
            >修改个人数据</el-button
        >
        <tiny-dialog-box
            v-model:visible="dialogVisible"
            title="修改个人数据"
            width="50%"
            main-height="100%"
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
                        label="姓名 "
                        prop="respondentName"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.respondentName"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item label="性别" prop="gender" class="w-30px">
                        <tiny-input
                            v-model="createData.gender"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="出生日期"
                        prop="totalAmount"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.totalAmount"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="身份证号"
                        prop="respondentIdNumber"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.respondentIdNumber"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="工作单位"
                        prop="overdueInterest"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.workarea"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="工作经历"
                        prop="workexperience"
                        class="w-100px"
                    >
                        <tiny-input
                            v-model="createData.workexperience"
                            style="width: 600px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="资历与证书"
                        prop="experience"
                        class="w-100px"
                    >
                        <tiny-input
                            v-model="createData.experience"
                            style="width: 600px"
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
                        label="学历"
                        prop="caseReason"
                        class="w-30%"
                    >
                        <tiny-input
                            v-model="createData.caseReason"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="电子邮箱"
                        prop="interestAmount"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.interestAmount"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="从业年限"
                        prop="penaltyAmount"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.penaltyAmount"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="擅长领域"
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
                <tiny-button type="primary" @click="updateUserData">
                    确定
                </tiny-button>
                <tiny-button @click="dialogVisible = false"> 取消 </tiny-button>
            </template>
        </tiny-dialog-box>
    </div>
</template>
<script setup>
import { onMounted, ref } from 'vue'
import {
    TinyButton,
    TinyDialogBox,
    TinyDivider,
    TinyForm,
    TinyFormItem,
    TinyInput,
    Modal,
} from '@opentiny/vue'
import ProfileField from '../profile/ProfileField.vue'
import ProfileSection from '../profile/ProfileSection.vue'
import axios from 'axios'

const userData = ref({
    name: '李林',
    gender: '男',
    borndate: '1989,7,7',
    identitynumber: '320123198907073023',
    education: '博士',
    email: 'lilin0707@outlook.com',
    worktime: '7年',
    goodarea: '民事调解',
    workexperience: '1981年-2001年在天津和平律师事务所工作20年',
    experience: '2002年获得全球最佳调解员金奖，2025年获得全国最佳调解员银牌',
    phonenumber: '18279669823',
    workarea: '北京朝阳调解员',
})

const dialogVisible = ref(false)

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
    workexperience: '',
    experience: '',
    workarea: '',
})

const fetchUserData = async () => {
    const userid = localStorage.getItem('userid')
    try {
        let res = await axios.get(
            `/ls/logic/respondent/personal_information/get_user?type=${3}&user_id=${userid}`
        )
        if (res.data.code === 200) {
            const data = res.data.data[0]
            userData.value = {
                name: data.name,
                gender: data.gender ? '男' : '女',
                borndate: data.bornTime,
                identitynumber: data.idCardNumber,
                education: data.educationLevel,
                email: data.email,
                worktime: data.experienceYears,
                goodarea: data.areasOfExpertise,
                workexperience: data.workExperience,
                experience: data.qualifications,
                phonenumber: data.phoneNumber,
                workarea: data.employer,
            }
        }
    } catch {
        Modal.message('连接超时')
    }
}

const handleChangeClick = () => {
    dialogVisible.value = true
    createData.value = {
        respondentName: userData.value.name,
        respondentIdNumber: userData.value.identitynumber,
        gender: userData.value.gender,
        applicantName: '',
        caseReason: userData.value.education,
        totalAmount: userData.value.borndate,
        principalAmount: '',
        interestAmount: userData.value.email,
        penaltyAmount: userData.value.worktime,
        overdueInterest: userData.value.goodarea,
        workexperience: userData.value.workexperience,
        experience: userData.value.experience,
        workarea: userData.value.workarea,
    }
}

const updateUserData = async () => {
    dialogVisible.value = false
    const data = {
        mediators: {
            areasOfExpertise: createData.value.overdueInterest,
            bornTime: createData.value.totalAmount,
            educationLevel: createData.value.caseReason,
            email: createData.value.interestAmount,
            employer: userData.value.workarea,
            experienceYears: userData.value.worktime,
            expertise: userData.value.goodarea,
            gender: userData.value.gender === '男',
            idCardNumber: createData.value.respondentIdNumber,
            mediatorId: 0,
            name: createData.value.respondentName,
            organizationId: 0,
            phoneNumber: userData.value.phonenumber,
            qualifications: createData.value.experience,
            status: true,
            type: 0,
            workExperience: createData.value.workexperience,
        },
        user: {
            email: createData.value.interestAmount,
            gender: userData.value.gender === '男' ? 1 : 0,
            idCard: createData.value.respondentIdNumber,
            identityType: '',
            name: createData.value.respondentName,
            passwordHash: '',
            phoneNumber: userData.value.phonenumber,
            status: true,
            userId: 0,
        },
    }
    try {
        let res = await axios.post(
            `/ls/logic/respondent/personal_information/update_user`,
            data
        )
        if (res.data.code === 200) {
            fetchUserData()
        }
    } catch {
        Modal.message('连接超时')
    }
}

onMounted(() => {
    fetchUserData()
})
</script>
<style scoped></style>
