package nc

import org.springframework.stereotype.Service

interface SampleService {
    fun sayHello(name: String): String
}

@Service
class SampleServiceImpl : SampleService {

    override fun sayHello(name: String): String {
        return "Hello ${name}"
    }

}