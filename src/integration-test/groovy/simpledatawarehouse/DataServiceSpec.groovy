package simpledatawarehouse

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DataServiceSpec extends Specification {

    DataService dataService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Data(...).save(flush: true, failOnError: true)
        //new Data(...).save(flush: true, failOnError: true)
        //Data data = new Data(...).save(flush: true, failOnError: true)
        //new Data(...).save(flush: true, failOnError: true)
        //new Data(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //data.id
    }

    void "test get"() {
        setupData()

        expect:
        dataService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Data> dataList = dataService.list(max: 2, offset: 2)

        then:
        dataList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        dataService.count() == 5
    }

    void "test delete"() {
        Long dataId = setupData()

        expect:
        dataService.count() == 5

        when:
        dataService.delete(dataId)
        sessionFactory.currentSession.flush()

        then:
        dataService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Data data = new Data()
        dataService.save(data)

        then:
        data.id != null
    }
}
