package base

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.restassured.RestAssured
import io.restassured.config.ObjectMapperConfig
import io.restassured.config.RestAssuredConfig
import org.testng.annotations.AfterSuite
import org.testng.annotations.BeforeSuite
import java.io.File
import java.io.FileInputStream
import java.util.*


open class BaseTest {

    companion object {
        lateinit var happiestBabyBaseUri: String

        lateinit var userRegisterEndPoint: String

        private val f: String = File.separator
        private val envFilePath: String = "${f}src${f}test${f}resources${f}environments${f}"
        private val csvFilePath: String = "${f}src${f}test${f}resources${f}testdata${f}csv${f}"

        val registerPositiveScenariosCSV = "${csvFilePath}${f}user${f}registerPositiveScenarios.csv"
        val registerNegativeScenariosCSV = "${csvFilePath}${f}user${f}registerNegativeScenarios.csv"
    }

    private fun initializeProperties() {

        // environment properties
        val envFileName = when (System.getProperty("env") == null) {
            true -> "dev"
            false -> System.getProperty("env").lowercase()
        }
        val environmentProperties = Properties()
        environmentProperties.load(
            FileInputStream(System.getProperty("user.dir") + envFilePath + envFileName + ".properties")
        )

        happiestBabyBaseUri = environmentProperties.getProperty("HAPPIEST_BABY_BASE_URI")

        // endpoint properties
        val endPointProperties = Properties()
        endPointProperties.load(
            FileInputStream(System.getProperty("user.dir") + envFilePath + "path.properties")
        )

        userRegisterEndPoint = endPointProperties.getProperty("USER_REGISTER_ENDPOINT")
    }

    private fun configRestAssured() {
        RestAssured.config =
            RestAssuredConfig.config().objectMapperConfig(ObjectMapperConfig().jackson2ObjectMapperFactory { _, _ ->
                val objectMapper = ObjectMapper()
                objectMapper.registerKotlinModule().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                objectMapper
            })
    }

    @BeforeSuite(alwaysRun = true)
    fun environmentSetUp() {
        initializeProperties()
        configRestAssured()
        println("\n *** Test execution started *** \n")
    }

    @AfterSuite(alwaysRun = true)
    fun environmentTearDown() {
        println("\n *** Test execution completed *** \n")
    }
}