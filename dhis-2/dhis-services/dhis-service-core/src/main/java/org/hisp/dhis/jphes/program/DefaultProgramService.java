package org.hisp.dhis.jphes.program;

import com.google.api.client.util.Lists;
import org.hisp.dhis.dataelement.DataElement;
import org.hisp.dhis.dataelement.DataElementService;
import org.hisp.dhis.indicator.Indicator;
import org.hisp.dhis.program.ProgramDataElement;

import java.util.Collections;
import java.util.List;

/**
 * Created by xenial on 1/4/17.
 */
public class DefaultProgramService implements ProgramService {

    public ProgramStore getProgramStore() {
        return programStore;
    }

    public void setProgramStore(ProgramStore programStore) {
        this.programStore = programStore;
    }

    private ProgramStore programStore;

    private DataElementService dataElementService;

    public void setDataElementService(DataElementService dataElementService) {
        this.dataElementService = dataElementService;
    }

    /**
     * adds a new program
     *
     * @param program to add
     * @return 1 if successful
     */
    @Override
    public int addProgram(Program program) {
        return programStore.save(program);
    }

    /**
     * Updates a Program
     *
     * @param program to update
     */
    @Override
    public void updateProgram(Program program) {
        programStore.update(program);
    }

    /**
     * deletes a Program
     *
     * @param program to delete
     */
    @Override
    public void deleteProgram(Program program) {
        programStore.delete(program);
    }

    /**
     * retrieves a program by id
     *
     * @param id of the program to be retreived
     * @return a Program
     */
    @Override
    public Program getProgram(int id) {
        return programStore.get(id);
    }

    /**
     * retieves a program by uid
     *
     * @param uid of the program to be retrieved
     * @return
     */
    @Override
    public Program getProgram(String uid) {
        return programStore.getByUid(uid);
    }

    /**
     * retrieves program by code
     *
     * @param code of the program to be retrieved
     * @return
     */
    @Override
    public Program getProgramByCode(String code) {
        return programStore.getByCode(code);
    }

    /**
     * @param name
     * @return
     */
    @Override
    public List<Program> getProgramByName(String name) {
        return programStore.searchProgramByName(name);
    }

    /**
     * retrieves all programs
     *
     * @return a list of available programs
     */
    @Override
    public List<Program> getAllPrograms() {
        return programStore.getAll();
    }

    @Override
    public List<Program> getProgramsBetween(int start, int end) {
        return programStore.getAllOrderedName(start,end);
    }

    @Override
    public List<Program> getProgramsBetweenByName(String name, int start, int end) {
        return programStore.getAllLikeName(name, start, end);
    }

    @Override
    public int getProgramCount() {
        return programStore.getProgramCount();
    }

    @Override
    public int getProgramCountByName(String name) {
        return programStore.getCountLikeName(name);
    }

}
