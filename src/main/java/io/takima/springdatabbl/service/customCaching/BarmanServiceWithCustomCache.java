//package io.takima.springdatabbl.service.customCaching;
//
//import io.takima.springdatabbl.dao.BarmanRepository;
//import io.takima.springdatabbl.model.Barman;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional(readOnly = true)
//public class BarmanServiceWithCustomCache {
//
//    @Autowired
//    private CustomCacheManager customCacheManager; // Inject the custom cache manager
//
//    @Autowired
//    private BarmanRepository barmanRepository;
//
//    // Your existing code
//
//    public Barman findById(Long id) {
//        Barman cachedBarman = (Barman) customCacheManager.getCache("barmans").get(id).get();
//        if (cachedBarman != null) {
//            return cachedBarman;
//        } else {
//            Barman barman = barmanRepository.findById(id).orElse(null);
//            if (barman != null && barman.getName().length() < 5) {
//                customCacheManager.getCache("barmans").put(id, barman);
//            }
//            return barman;
//        }
//    }
//
//    public void removeBarman(Long id) {
//        customCacheManager.getCache("barmans").evict(id);
//        barmanRepository.deleteById(id);
//    }
//
//    @Transactional
//    public Barman updateBarman(Long id, Barman barman) {
//        Barman updatedBarman = barmanRepository.save(barman);
//        if (updatedBarman != null && updatedBarman.getName().length() < 5) {
//            customCacheManager.getCache("barmans").put(id, updatedBarman);
//        }
//        return updatedBarman;
//    }
//}
