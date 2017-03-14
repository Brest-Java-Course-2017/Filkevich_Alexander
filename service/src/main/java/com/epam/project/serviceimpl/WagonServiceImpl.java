package com.epam.project.serviceimpl;

import com.epam.project.dao.WagonDao;
import com.epam.project.model.Wagon;
import com.epam.project.service.WagonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by master on 14.3.17.
 */
@Service
@Transactional
public class WagonServiceImpl implements WagonService {

    private static final Logger LOGGER= LogManager.getLogger();

    @Autowired
    private WagonDao wagonDao;

    public void setWagonDao(WagonDao wagonDao) {
        this.wagonDao = wagonDao;
    }

    @Override
    public List<Wagon> getAllWagonByDepo(Integer idDepo) throws DataAccessException {
        LOGGER.debug("getAllWagonByDepo",idDepo);
        Assert.notNull(idDepo,"must be not null");
        return wagonDao.getAllWagonByDepo(idDepo);
    }

    @Override
    public void addWagon(Wagon wagon) throws DataAccessException {
        LOGGER.debug("addWagon", wagon);
        Assert.notNull(wagon,"wagon musn't be null");
        Assert.notNull(wagon.getId(),"wagon must be have id");
        Assert.hasText(wagon.getType(),"wagon must have id");
        Assert.notNull(wagon.getDateOfBuilder(),"must have date");
        try {
            wagonDao.addWagon(wagon);
        }
        catch (Exception e){
            LOGGER.debug("addWagon have exception",e);
            throw new IllegalArgumentException();
        }

    }

    @Override
    public Wagon getWagonById(Integer id) throws DataAccessException {
        LOGGER.debug("getwagonById",id);
        Assert.notNull(id);
        Assert.isTrue(id>0);
        Wagon wagon;
        try {
            wagon=wagonDao.getWagonById(id);
            Assert.notNull(wagon);
        }
        catch (Exception e){
            LOGGER.debug("getWagonById have Exception",e);
            throw new IllegalArgumentException();
        }
        return wagon;
    }

    @Override
    public int updateWagon(Wagon wagon) throws DataAccessException {
        LOGGER.debug("updateWagon",wagon);
        Assert.notNull(wagon);
        Assert.isTrue(wagon.getId()>0);
        Assert.hasText(wagon.getType(),"wagon must have type");
        Assert.notNull(wagon.getDateOfBuilder(),"wagon must have date of build");
        try {
            Wagon testWagon=wagonDao.getWagonById(wagon.getId());
            Assert.notNull(testWagon,"if update , than wagon must existed in db");
        }
        catch (Exception e){
            LOGGER.debug("updateWagon have exception",wagon);
            throw new IllegalArgumentException();
        }
        return wagonDao.updateWagon(wagon);
    }

    @Override
    public int deleteWagon(Integer id) throws DataAccessException {
        return 0;
    }

    @Override
    public Integer countWagonByDepo(Integer idDepo) throws DataAccessException {
        return null;
    }

    @Override
    public Integer sumOfSeatsByDepo(Integer idDepo) throws DataAccessException {
        return null;
    }

    @Override
    public List<Wagon> getWagonByDate(LocalDate from, LocalDate to) throws DataAccessException {
        return null;
    }
}
