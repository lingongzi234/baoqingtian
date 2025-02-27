import { createMemoryHistory, createRouter } from 'vue-router'
import HomeView from "../page/Home.vue"
import LoginView from '../page/Login.vue'
import ManagerComponent from "../page/component/Manager/ManagerComponent.vue";
import UserComponent from "../page/component/User/UserComponent.vue";
import ApplicantComponent from "../page/component/Applicant/ApplicantComponent.vue";
import Register from "../page/Register.vue";
import NewTaskComponent from "../page/component/Applicant/NewTaskComponent.vue";
import UserDataComponent from "../page/component/User/UserDataComponent.vue";
import Addcase from '../page/Addcase.vue';
import ManageTaskComponent from "../page/component/Manager/ManageTaskComponent.vue";
import ManageYesTaskComponent from "../page/component/Manager/ManageYesTaskComponent.vue";
import ManageNoTaskComponent from "../page/component/Manager/ManageNoTaskComponent.vue";
import ApplicantDataComponent from "../page/component/Applicant/ApplicantDataComponent.vue";
import UserTaskComponent from "../page/component/User/UserTaskComponent.vue";
import UserNoTaskComponent from "../page/component/User/UserNoTaskComponent.vue";
import UserYesTaskComponent from "../page/component/User/UserYesTaskComponent.vue";
import ApplicantDataStatistic from "../page/component/Applicant/ApplicantDataStatistic.vue";
import UserTimeTaskComponent from "../page/component/User/UserTimeTaskComponent.vue";
import UserDataStatistic from "../page/component/User/UserDataStatistic.vue";
import ManageDataStatistic from "../page/component/Manager/ManageDataStatistic.vue";


const routes = [
    { path: '/', component: LoginView },
    {path: '/login', component: LoginView},
    {path: '/register', component: Register},
    {
        path: '/managercomponent', component: ManagerComponent,children:[
            {
                path: '/managetaskcomponent', component: ManageTaskComponent,children:[
                    {
                        path: '/manageyescomponent', component: ManageYesTaskComponent
                    },
                    {
                        path: '/managenotaskcomponent', component: ManageNoTaskComponent
                    }
                ]
            },
            {
                path: '/managedatastatistic', component: ManageDataStatistic
            }
        ]

    },
    {
        path: '/usercomponent', component: UserComponent,children:[
            {
                path: '/usertaskcomponent', component: UserTaskComponent,children:[
                    {
                        path: '/useryestaskcomponent', component: UserYesTaskComponent
                    },
                    {
                        path: '/usernotaskcomponent', component: UserNoTaskComponent
                    },

                ],
            },
            {
                path: '/userdatastatistic', component: UserDataStatistic

            },
            {
                path: '/userdatacomponent', component: UserDataComponent
            },
            {
                path: '/usertimetaskcomponent', component: UserTimeTaskComponent
            },
            {
                path: '', component: UserTimeTaskComponent
            },
        ]

    },
    { path: '/home', component: HomeView },
    { path: '/applicantcomponent', component: ApplicantComponent,children: [
            {
                path: '/newtaskcomponent', component: NewTaskComponent
            },
            {
                path: '/applicantdatacomponent', component: ApplicantDataComponent
            },
            {
                path: '/applicantdatastatistic', component: ApplicantDataStatistic
            },
            {
                path: '', component: ApplicantDataStatistic
            },
        ]},
        {path: '/apitest', component: Addcase},
]

export default createRouter({
    history: createMemoryHistory(),
    routes,
})
