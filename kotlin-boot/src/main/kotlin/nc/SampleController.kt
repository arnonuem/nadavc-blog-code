package nc

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

data class HelloRequest(val requestedBy: String)

@RestController
class SampleController @Autowired constructor(val sampleService: SampleService) {

    val logger = LoggerFactory.getLogger(SampleController::class.java)

    @RequestMapping(value = "/hello", method = arrayOf(RequestMethod.POST))
    fun hello(@RequestBody request: HelloRequest): String {
        return sampleService.sayHello(request.requestedBy)
    }

}