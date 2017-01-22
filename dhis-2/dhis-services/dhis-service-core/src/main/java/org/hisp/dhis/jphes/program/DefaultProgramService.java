package org.hisp.dhis.jphes.program;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by xenial on 1/4/17.
 */
public class DefaultProgramService implements ProgramService {

    public static final Log log = LogFactory.getLog( DefaultProgramService.class );

    @Autowired
    private ProgramStore programStore;

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
        return programStore.getAllLikeName( name );
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
        return programStore.getAll( start, end );
    }

    @Override
    public List<Program> getProgramsBetweenByName(String name, int start, int end) {
        return programStore.getAllLikeName(name, start, end);
    }

    @Override
    public int getProgramCount() {
        return programStore.getCount();
    }

    @Override
    public int getProgramCountByName(String name) {
        return programStore.getCountLikeName( name );
    }
}
