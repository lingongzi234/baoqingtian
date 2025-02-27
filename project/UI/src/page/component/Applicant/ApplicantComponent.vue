<template>
    <div class="b-#E6F2FF w-screen h-screen flex">
        <div class="bg-#E6F2FF w-15% h-100%">
            <div class="w-100% h-30%">
                <img
                    class="h-20 w-20 rounded-full pos-absolute left-10 top-20"
                    src="/img_1.png"
                    alt="dog"
                />
                <p class="text-black pos-absolute top-22 left-35 text-6"
                    >普通用户</p
                >
            </div>
            <div class="w-100% h-10% c-black pos-relative"
                ><router-link to="/applicantdatacomponent"
                    ><tiny-button
                        type="large"
                        :icon="TinyIconSearch"
                        class="bg-#dce8f5 hover:bg-blue pos-absolute top-0 left-5 border-solid border-white hover-accent-blue w-50"
                    >
                        个人信息</tiny-button
                    ></router-link
                ></div
            >
            <div class="w-100% h-10% c-black pos-relative"
                ><router-link to="/newtaskcomponent"
                    ><tiny-button
                        type="large"
                        :icon="TinyIconYes"
                        class="bg-#dce8f5 hover:bg-blue pos-absolute top-0 left-5 border-solid border-white hover-accent-blue w-50"
                    >
                        案件管理</tiny-button
                    ></router-link
                ></div
            >
            <div class="w-100% h-10% c-black pos-relative"
                ><router-link to="/applicantdatastatistic"
                    ><tiny-button
                        type="large"
                        :icon="TinyIconSearch"
                        class="bg-#dce8f5 hover:bg-blue pos-absolute top-0 left-5 border-solid border-white hover-accent-blue w-50"
                    >
                        数据统计</tiny-button
                    ></router-link
                ></div
            >

            <div class="w-100% h-10% c-black pos-relative"></div>
            <div class="w-100% h-10% c-black pos-relative"
                ><tiny-button
                    type="large"
                    :icon="IconDerive"
                    class="bg-#dce8f5 hover:bg-blue pos-absolute top-0 left-10 border-solid border-white hover-accent-blue"
                    @click="goback"
                >
                </tiny-button
                ><router-link to="/login">
                    <p class="pos-absolute top--1 left-27 c-black">退出登录</p>
                </router-link></div
            >
        </div>

        <div class="bg-#E6F2FF w-1% h-full">
            <div class="bg-#E6F2FF h-10%"></div>
            <tiny-divider
                class="divider"
                direction="vertical"
                :style="{ height: '600px', borderTopWidth: '5px' }"
            ></tiny-divider>
        </div>
        <div class="w-84% h-100% bg-#E6F2FF"><router-view></router-view></div>
    </div>
</template>
<script setup>
import { TinyDivider, TinyButton } from '@opentiny/vue'
import { useRouter } from 'vue-router'
import {
    iconDel,
    iconYes,
    iconEdit,
    iconMail,
    iconStarO,
    iconSearch,
    iconDerive,
    iconCustomerService,
    iconUser,
} from '@opentiny/vue-icon'
const router = useRouter()
const TinyIconDel = iconDel()
const TinyIconYes = iconYes()
const TinyIconEdit = iconEdit()
const TinyIconMail = iconMail()
const TinyIconStarO = iconStarO()
const TinyIconSearch = iconSearch()
const IconCustomerService = iconCustomerService()
const IconDerive = iconDerive()
const TinyIconUser = iconUser()
const goback = () => {
    router.push((name = 'login'))
}
</script>
<style scoped></style>
