<template>
    <button @click="addCase">AddCase</button>
    <button @click="addMediator">AddMediator</button>
    <button @click="addRespondent">AddRespondent</button>
    <br />
    <button @click="adminCaseSelect">AdminCaseSelect</button>
    <button @click="applicantSelect">ApplicantSelect</button>
    <br />
    <button @click="addRespondent">AddRespondent</button>
    <br />
    <button @click="adminCaseSelect">AdminCaseSelect</button>
    <button @click="applicantSelect">ApplicantSelect</button>
    <br />
    <button @click="gotoLogin">GotoLogin</button>
    <button @click="resetPassword">ResetPassword</button>
    <button @click="logout">Logout</button>
    <br />
    <br />
    <button @click="updateInfo">UpdateInfo</button>
    <button @click="updateCase">UpdateCase</button>
    <button @click="reserve">Reserve</button>
    <button @click="respondentCase">RespondentCase</button>
    <button @click="respondentCaseInfo">RespondentCaseInfo</button>
    <br />
    <div
        >调解员
        <button @click="mediatorGetCase">GetCase</button>
    </div>
    <div>
        被申请人
        <button @click="respondentChose">RespondentChose</button>
        <button @click="getPlan">GetPlan</button>
    </div>
    <div
        >申请人
        <button @click="applicantSelectPage">ApplicantSelectPage</button>
    </div>
</template>

<script setup>
import axios from 'axios'
import { useRouter } from 'vue-router'
const router = useRouter()
async function addCase() {
    const data = {
        caseReason: '',
        interestAmount: 1,
        mediationStatus: 1,
        overdueInterest: 1,
        penaltyAmount: 1,
        principalAmount: 1,
        respondentIdNumber: '',
        status: 1,
        totalAmount: 1,
    }
    let res = await axios.post('/custom/logic/cases/addCase', data)
    console.log(res)
}

async function addMediator() {
    const data = {
        areasOfExpertise: '123',
        bornTime: '2024-01-01',
        educationLevel: '123',
        email: 'aadfsfa@casd.com',
        employer: '213',
        experienceYears: 12,
        expertise: '123',
        gender: true,
        idCardNumber: '111111111111111111',
        mediatorId: 12,
        name: '666',
        organizationId: 123,
        phoneNumber: '11451411111',
        qualifications: '123',
        status: true,
        type: 0,
        workExperience: '123',
    }
    let res = await axios.post('/custom/logic/administrator/addMediator', data)
    if (res.data.code === 200) {
        if (res.data.data) {
            console.log('Add Mediator success')
        } else {
            console.log('Add Mediator fail')
        }
    }
    console.log(res)
}

async function adminCaseSelect() {
    const data = {
        caseReason: '',
        mediatorStatus: '',
        respondentIdNumber: '',
        status: '',
    }
    let res = await axios.post(
        '/custom/logic/administrator/caseConditionSelect',
        data
    )
    console.log(res)
}

async function resetPassword() {
    const data = {
        phone: '13190540999',
        password: 'Ggg123457',
    }
    let res = await axios.post(
        '/custom/logic/inout/changePassword?phone=13190540999&password=Ggg123457',
        data
    )
    console.log(res)
}

async function logout() {
    let res = await axios.get('/custom/logic/inout/logout')
    console.log(res)
}

async function updateInfo() {
    const data = {
        idCardNumber: '222222222222222222',
    }
    let res = await axios.post('/custom/logic/inout/updateInfo', data)
    console.log(res)
}

async function updateCase() {
    const caseData = {
        caseId: 39,
        respondentName: '张三',
        applicantId: 24,
        respondentId: null,
        mediatorId: null,
        organizationId: null,
        totalAmount: 1000,
        principalAmount: 100,
        interestAmount: 101,
        penaltyAmount: 120,
        overdueInterest: 212,
        // status: 1,
        // mediationStatus: 0,
        // status: 1,
        // mediationStatus: 0,
        caseReason: '法外狂',
        respondentIdNumber: '111111111111111111',
        appointmentTime: null,
    }
    let res = await axios.post('/custom/logic/cases/updateCase', caseData)
    console.log(res)
}

async function applicantSelect() {
    const data = {
        caseReason: '',
        gender: '',
        idCardNumber: '',
        mediatorStatus: '',
        name: '',
        respondentIdNumber: '',
        status: '',
    }
    let res = await axios.post('/custom/logic/cases/conditionSelect', data)
    console.log(res)
}

async function gotoLogin() {
    router.replace('/login')
}

async function addRespondent() {
    const data = {
        phone_number: '13190540999',
        respondent_info: {
            address: 'home',
            companyName: 'bu',
            creditScore: 0,
            income: 0,
            jobTitle: '',
            respondentId: 0,
        },
    }
    let res = await axios.post('/ls/logic/assistant/add_respondent', data)
    console.log(res)
}

async function reserve() {
    const data = {
        case_id: 39,
        phone_number: '13190540999',
        respondent_info: {
            address: '',
            companyName: '',
            creditScore: 0,
            income: 0,
            jobTitle: '',
            respondentId: 0,
        },
        time: '2024-01-01 00:00:00',
        user_id: 29,
    }
    let res = await axios.post('/ls/logic/assistant/reserve', data)
    console.log(res)
}

async function respondentCase() {
    let res = await axios.get(`/ls/logic/respondent/case?id=${21}`)
    console.log(res)
}

async function respondentCaseInfo() {
    let res = await axios.get(`/ls/logic/respondent/information?case_id=${39}`)
    console.log(res)
}

async function mediatorGetCase() {
    let res = await axios.post(`/custom/logic/mediator/getCases`, {
        status: 0,
    })
    console.log(res)
}

async function respondentChose() {
    let res = await axios.post(`/ls/logic/respondent/choose`, {
        case_id: 39,
        times: 0,
    })
    console.log(res)
}

async function getPlan() {
    let res = await axios.get(`/ls/logic/respondent/plan?case_id=${39}`)
    console.log(res)
}

async function applicantSelectPage() {
    let res = await axios.post(`/custom/logic/cases/conditionSelect`, {
        caseReason: '',
        current: 0,
        gender: '',
        idCardNumber: '',
        mediatorId: 0,
        mediatorStatus: '',
        name: '',
        respondentIdNumber: '',
        size: 0,
        status: '',
    })
    console.log(res)
}
</script>
<style>
button {
    border: 1px solid #122;
    margin: 10px;
}
</style>
<style>
button {
    border: 1px solid #122;
    margin: 10px;
}
</style>
