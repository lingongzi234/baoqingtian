<template>
    <div class="w-screen flex justify-center items-center h-screen">
        <div
            class="demo-form w-2xl shadow-2xl rounded-md h-md flex items-center"
        >
            <tiny-form
                class="pl-12 pr-16"
                ref="ruleFormRef"
                :model="createData"
                :rules="rules"
                label-width="90px"
                label-position="left"
            >
                <tiny-form-item label="电话" prop="phone" required>
                    <tiny-input v-model="createData.phone"></tiny-input>
                </tiny-form-item>
                <tiny-form-item label="密码" prop="password">
                    <tiny-input
                        v-model="createData.password"
                        type="password"
                        show-password
                    ></tiny-input>
                </tiny-form-item>
                <tiny-form-item>
                    <tiny-button
                        class="mr-[60px]"
                        type="primary"
                        @click="handleSubmit"
                    >
                        注册
                    </tiny-button>
                    <tiny-button
                        class="mr-[60px]"
                        type="primary"
                        @click="goLogin"
                    >
                        返回
                    </tiny-button>
                </tiny-form-item>
            </tiny-form>
        </div>
    </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import {
    TinyForm,
    TinyFormItem,
    TinyInput,
    TinyButton,
    TinyModal,
    Modal,
} from '@opentiny/vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
const router = useRouter()

const createData = reactive({
    phone: '',
    password: '',
})
const rules = ref({
    phone: [
        { required: true, message: '必填', trigger: 'blur' },
        {
            validator: (rule, value, callback) => {
                if (!/^1[3,4,5,6,7,8,9][0-9]{9}$/.test(value)) {
                    callback(new Error('手机号错误'))
                } else {
                    callback()
                }
            },
        },
    ],
    password: [
        { required: true, message: '必填', trigger: 'blur' },
        {
            validator: (rule, value, callback) => {
                if (
                    !/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/.test(
                        value
                    )
                ) {
                    callback(
                        new Error(
                            '最少八个字符，至少包含一个大写字母，一个小写字母和一个数字'
                        )
                    )
                } else {
                    callback()
                }
            },
        },
    ],
})
const ruleFormRef = ref()

function handleSubmit() {
    ruleFormRef.value.validate(async (valid) => {
        if (valid) {
            let res
            let timeout = setTimeout(() => {}, 3000)
            try {
                res = await axios.post(
                    `/custom/logic/inout/open/registerByPhone?phone=${createData.phone}&password=${createData.password}`,
                    {
                        params: {
                            phone: createData.phone,
                            password: createData.password,
                        },
                    }
                )
            } catch {
                console.log('register connection fail')
                Modal.message('connection fail')
                return false
            }
            clearTimeout(timeout)
            Modal.message('register success')
            router.push('/login')
        } else {
            return false
        }
    })
}

function goLogin() {
    router.push('/login')
}
</script>
<style scoped></style>
