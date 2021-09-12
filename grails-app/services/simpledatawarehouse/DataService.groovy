package simpledatawarehouse

import grails.gorm.transactions.Transactional
import model.Dimension
import model.Time

@Transactional
class DataService {

    def calculateCTR(Dimension dimension) {
        Data.createCriteria().list {
            eq('datasource', dimension.datasource)
            eq('campaign', dimension.campaign)
            projections {
                sum('clicks') / sum('impressions')
            }
        }
    }

    def calculateTotalClicks(Dimension dimension, Time time) {
        Data.createCriteria().list {
            eq('datasource', dimension.datasource)
            between('daily', time.from, time.to)
            projections {
                sum('clicks')
            }
        }
    }

    def impressionsOverTime(Dimension dimension, Time time) {
        Data.createCriteria().list {
            eq('datasource', dimension.datasource)
            eq('campaign', dimension.campaign)
            between('daily', time.from, time.to)
            projections {
                sum('impressions')
            }
        }
    }

//    Generated Classes

    def get(Long id) {
        Data.findById(id)
    }

    def list(Map args) {
        Data.list()
    }

    void delete(Long id) {
        Data.findById(id).delete()
    }

    def save(Data entry) {
        return entry.save()
    }
}
