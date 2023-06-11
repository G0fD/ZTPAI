import {onMounted} from "vue";
import {useRouter} from "vue-router";
import {API_URL} from '@/common/constant';

export const useAuthenticated = () => {
    const router = useRouter();

    onMounted(() => {
        if (!sessionStorage.getItem('token')) {
            router.push({
                name: "login"
            });
            return;
        }
        fetch(API_URL + "/api/auth/validate", {
            headers: {
                'Authorization': `Bearer ${sessionStorage.getItem('token')}`
            }
        })
            .then(response => response.text())
            .then(data => {
                if (data !== "valid") {
                    console.log("not valid")
                    router.push({
                        name: "login"
                    });
                } else {
                    console.log(data)
                }
            })
            .catch(error => {
                console.error(error)
            });
    })
}