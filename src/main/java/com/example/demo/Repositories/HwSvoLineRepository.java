package com.example.demo.Repositories;

import com.example.demo.Models.Contact;
import com.example.demo.Models.CustomerSite;
import com.example.demo.Models.HwSvoLine;
import com.example.demo.Models.ServiceOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HwSvoLineRepository extends CrudRepository<HwSvoLine, Integer> {

    @Query(value = "select * from hw_svo_line\n" +
            "inner join SERVICE_ORDER_LINE\n" +
            "ON SERVICE_ORDER_LINE.svo_id = HW_SVO_LINE.svo_line_id\n" +
            "inner join SERVICE_ORDER\n" +
            "ON SERVICE_ORDER.svo_id = SERVICE_ORDER_LINE.svo_id\n" +
            "where SERVICE_ORDER.svo_id = :#{#serviceOrder.svoId}", nativeQuery = true)
    List<HwSvoLine> findByServiceOrder(@Param("serviceOrder") ServiceOrder serviceOrder);

    @Query(value = "select * from hw_svo_line inner join SERVICE_ORDER_LINE ON SERVICE_ORDER_LINE.svo_line_id = HW_SVO_LINE.svo_line_id inner join SERVICE_ORDER ON SERVICE_ORDER.svo_id = SERVICE_ORDER_LINE.svo_id where SERVICE_ORDER.svo_id = ?1", nativeQuery = true)
    List<HwSvoLine> findByServiceOrderId(int svoId);

//    @Query(value = "select * from hwSvoLine h\n" +
//            "inner join serviceOrderLine sl\n" +
//            "ON h.svoLineId=sl.svoLineId\n" +
//            "inner join serviceOrder so\n" +
//            "ON so.svoId = sl.svoId\n" +
//            "where so.svoId = ?1", nativeQuery = true)
//    List<HwSvoLine> findByServiceOrderIdNew(int svoId);

}
