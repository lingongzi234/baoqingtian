<template>
    <div
        class="profile-card text-xl flex flex-col items-center p-6 bg-gray-100 rounded-xl pos-relative left-60 top-40 shadow-md w-200 h-110"
    >
        <div class="flex flex-row items-center justify-start gap-12">
            <div class="gap-1 my-3 w-50% h-100%">
                <div class="flex gap-3 my-5">
                    <div class="font-bold">姓名:</div>
                    <div>{{ userData.name }}</div>
                </div>
                <div class="flex gap-3 my-5">
                    <div class="font-bold">联系方式:</div>
                    <div>{{ userData.phonenumber }}</div>
                </div>
                <div class="flex gap-3 my-5">
                    <div class="font-bold">电子邮箱:</div>
                    <div>{{ userData.email }}</div>
                </div>
                <div class="flex gap-3 my-5">
                    <div class="font-bold">身份证号:</div>
                    <div>{{ userData.identitynumber }}</div>
                </div>
                <div class="flex gap-3 my-5">
                    <div class="font-bold">身份证照片:</div
                    ><tiny-button @click="showIdentityImage = true" size="mini"
                        >点击查看</tiny-button
                    >
                </div>
                <div class="flex gap-3 my-5">
                    <div class="font-bold">我的身份:</div>
                    <div>{{ userData.identity }}</div>
                </div>
            </div>

            <tiny-divider
                class="divider"
                direction="vertical"
                :style="{ height: '300px', borderTopWidth: '5px' }"
            ></tiny-divider>
            <div class="gap-1 my-3 w-50% h-100%">
                <div class="flex gap-3 my-5">
                    <div class="font-bold">在职证明:</div
                    ><tiny-button @click="showWorkImage = true" size="mini"
                        >点击查看</tiny-button
                    >
                </div>
                <div class="flex gap-3 my-5">
                    <div class="font-bold">授权委托书:</div
                    ><tiny-button @click="showYesImage = true" size="mini"
                        >点击查看</tiny-button
                    >
                </div>
                <div class="flex gap-3 my-5">
                    <div class="font-bold">律师执业证号:</div>
                    <div>{{ userData.helpnumber }}</div>
                </div>
                <div class="flex gap-3 my-5">
                    <div class="font-bold">所在律师事务所:</div>
                    <div>{{ userData.helparea }}</div>
                </div>
                <div class="flex gap-3 my-5">
                    <div class="font-bold">律师执业证:</div
                    ><tiny-button @click="showHelpmanImage = true" size="mini"
                        >点击查看</tiny-button
                    >
                </div>
                <div class="flex gap-3 my-5">
                    <div class="font-bold">律师事务所函:</div
                    ><tiny-button @click="showHelpImage = true" size="mini"
                        >点击查看</tiny-button
                    >
                </div>
            </div>
        </div>

        <el-button
            class="b-black w-32 h-7.5 mt-6 bg-gray rounded-lg hover-bg-blue hover:cursor-pointer pos-absolute top-100 left-42%"
            @click="handleChangeClick"
            >修改个人数据</el-button
        >
        <tiny-dialog-box
            v-model:visible="dialogVisible"
            title="修改个人数据"
            width="50%"
            main-height="100%"
        >
            <div class="flex gap-16"
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
                        class="w-30px h-13"
                    >
                        <tiny-input
                            v-model="createData.respondentName"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="我的身份"
                        pr
                        op="identity"
                        class="w-30px h-13"
                    >
                        <tiny-input
                            v-model="createData.identity"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="身份证照片"
                        prop="identityImgSrc"
                        class="w-30px"
                    >
                        <tiny-pop-upload
                            size="mini"
                            :action="action"
                        ></tiny-pop-upload>
                    </tiny-form-item>
                    <tiny-form-item
                        label="电子邮箱"
                        prop="email"
                        class="w-30px"
                    >
                        <tiny-input
                            v-model="createData.email"
                            style="width: 200px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="身份证号"
                        prop="identitynumber"
                        class="w-100px"
                    >
                        <tiny-input
                            v-model="createData.identitynumber"
                            style="width: 600px"
                        ></tiny-input>
                    </tiny-form-item>
                    <tiny-form-item
                        label="律师职业证号"
                        prop="helpnumber"
                        class="w-100px"
                    >
                        <tiny-input
                            v-model="createData.helpnumber"
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
                        label="律师职业证"
                        prop="caseReason"
                        class="w-30%"
                    >
                        <tiny-pop-upload
                            size="mini"
                            :action="action"
                        ></tiny-pop-upload>
                    </tiny-form-item>
                    <tiny-form-item
                        label="律师事务所函"
                        prop="interestAmount"
                        class="w-30px"
                    >
                        <tiny-pop-upload
                            size="mini"
                            :action="action"
                        ></tiny-pop-upload>
                    </tiny-form-item>
                    <tiny-form-item
                        label="在职证明"
                        prop="penaltyAmount"
                        class="w-30px"
                    >
                        <tiny-pop-upload
                            size="mini"
                            :action="action"
                        ></tiny-pop-upload>
                    </tiny-form-item>
                    <tiny-form-item
                        label="授权委托书"
                        prop="overdueInterest"
                        class="w-30px"
                    >
                        <tiny-pop-upload
                            size="mini"
                            :action="action"
                        ></tiny-pop-upload>
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
        <tiny-dialog-box
            v-model:visible="showIdentityImage"
            title="身份证照片"
            width="50%"
            main-height="100%"
        >
            <div>
                <img alt="dada" src="/img_4.png" />
            </div>
        </tiny-dialog-box>
        <tiny-dialog-box
            v-model:visible="showHelpImage"
            title="律师事务所函"
            width="50%"
            main-height="100%"
        >
            <div>
                <img alt="dadadada" src="/img_5.png" />
            </div>
        </tiny-dialog-box>
        <tiny-dialog-box
            v-model:visible="showWorkImage"
            title="在职证明"
            width="50%"
            main-height="100%"
        >
            <div>
                <img alt="dadadadadada" src="/img_8.png" />
            </div>
        </tiny-dialog-box>
        <tiny-dialog-box
            v-model:visible="showYesImage"
            title="授权委托书"
            width="50%"
            main-height="100%"
        >
            <div>
                <img alt="dadaadadad" src="/img_7.png" />
            </div>
        </tiny-dialog-box>
        <tiny-dialog-box
            v-model:visible="showHelpmanImage"
            title="律师执业证"
            width="50%"
            main-height="100%"
        >
            <div>
                <img alt="dadadda" src="/img_6.png" />
            </div>
        </tiny-dialog-box>
    </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import {
    TinyButton,
    TinyDialogBox,
    TinyDivider,
    TinyForm,
    TinyFormItem,
    TinyInput,
    TinyPopUpload,
    Modal,
} from '@opentiny/vue'

//use ref for userdata
const userData = ref({
    name: '王丽',
    identity: '律师',
    helpnumber: '7729809',
    identitynumber: '301201199906248012',
    email: '232123849822@qq.com',
    helparea: '北京朝阳律师事务所',
    phonenumber: '13982094999',
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
})
const showIdentityImage = ref(false)
const showWorkImage = ref(false)
const showHelpmanImage = ref(false)
const showYesImage = ref(false)
const showHelpImage = ref(false)
const identityImgSrc = ref(
    'https://www.keaitupian.cn/cjpic/frombd/0/253/4001167109/3893799730.jpg'
)
const WorkImgeSrc = ref(
    'https://www.keaitupian.cn/cjpic/frombd/0/253/4001167109/3893799730.jpg'
)
const HelpmanImgeSrc = ref(
    'https://www.keaitupian.cn/cjpic/frombd/0/253/4001167109/3893799730.jpg'
)
const YesImgeSrc = ref(
    'https://www.keaitupian.cn/cjpic/frombd/0/253/4001167109/3893799730.jpg'
)
const HelpImgeSrc = ref(
    'https://www.keaitupian.cn/cjpic/frombd/0/253/4001167109/3893799730.jpg'
)

/**
applicantId: 3
​
applicationDate: "2025-01-11"
​
contactPhone: "1"
​
createdAt: "2025-01-02T06:13:41.000+00:00"
​
email: null
​
employmentCertificateAddress: "1"
​
gender: 1
​
idCard: null
​
idCardNumber: null
​
idPhotoAddress: "1"
​
identityType: "0"
​
lawAddress: "1"
​
lawFirm: "1"
​
lawyerAddress: "1"
​
lawyerNumber: "1"
​
name: "用户13190540999"
​
organizationName: "1"
​
passwordHash: "cd17bce4b76d42c7466e86e0d818b4b114e69f081ade1d2850fc5a940c40cccd"
​
phoneNumber: "13190540999"
​
powerAttorney: "1"
​
profession: "1"
​
status: false
​
updatedAt: "2025-01-04T07:56:32.000+00:00"
​
userId: 29
 */
const fetchUserData = async () => {
    const userid = localStorage.getItem('userid')
    try {
        let res0 = await axios.get(
            `/ls/logic/respondent/personal_information/get_user?type=${0}&user_id=${userid}`
        )
        let res1 = await axios.get(
            `/ls/logic/respondent/personal_information/get_user?type=${1}&user_id=${userid}`
        )
        if (res0.data.code === 200 && res1.data.code === 200) {
            const dataLst = res0.data.data.concat(res1.data.data)
            const data = { ...dataLst[0], ...dataLst[1] }
            console.log(data)
            localStorage.setItem('username', data.name)
            userData.value = {
                name: data.name,
                identity: data.profession,
                helpnumber: data.lawyerNumber,
                identitynumber: data.idCardNumber,
                email: data.email,
                helparea: data.lawFirm,
                phonenumber: data.phoneNumber,
                ...data.value,
            }
        }
    } catch (error) {
        Modal.message('连接超时')
    }
}

const handleChangeClick = () => {
    dialogVisible.value = true
    createData.value = {
        respondentName: userData.value.name,
        respondentIdNumber: userData.value.identitynumber,
        applicantName: '',
        caseReason: userData.value.education,
        totalAmount: userData.value.borndate,
        principalAmount: '',
        interestAmount: userData.value.email,
        penaltyAmount: userData.value.worktime,
        overdueInterest: userData.value.goodarea,
        workexperience: userData.value.workexperience,
        experience: userData.value.experience,
        ...userData.value,
    }
}

const updateUserData = async () => {
    dialogVisible.value = false
    const userid = localStorage.getItem('userid')
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
            userId: userid,
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
    } catch (error) {
        Modal.message('连接超时')
    }
}

onMounted(() => {
    fetchUserData()
})
</script>
<style scoped></style>
