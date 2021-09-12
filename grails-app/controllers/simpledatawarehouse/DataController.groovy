package simpledatawarehouse

import grails.validation.ValidationException
import model.Dimension
import model.Time

import static org.springframework.http.HttpStatus.*

class DataController {

    DataService dataService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def calculateTotalClicks(Dimension dimension, Time time) {
        dataService.calculateTotalClicks(dimension, time)
    }

    def calculateCTR(Dimension dimension) {
        dataService.calculateCTR(dimension)
    }

    def impressionsOverTime(Dimension dimension, Time time) {
        dataService.impressionsOverTime(dimension, time)
    }


//    Generated classes

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond dataService.list(params), model:[dataCount: dataService.count()]
    }

    def show(Long id) {
        respond dataService.get(id)
    }

    def create() {
        respond new Data(params)
    }

    def save(Data data) {
        if (data == null) {
            notFound()
            return
        }

        try {
            dataService.save(data)
        } catch (ValidationException e) {
            respond data.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'data.label', default: 'Data'), data.id])
                redirect data
            }
            '*' { respond data, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond dataService.get(id)
    }

    def update(Data data) {
        if (data == null) {
            notFound()
            return
        }

        try {
            dataService.save(data)
        } catch (ValidationException e) {
            respond data.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'data.label', default: 'Data'), data.id])
                redirect data
            }
            '*'{ respond data, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        dataService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'data.label', default: 'Data'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'data.label', default: 'Data'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
