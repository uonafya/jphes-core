package org.hisp.dhis.jphes.program;

import java.util.List;

/**
 * Created by xenial on 1/4/17.
 */
public interface ProgramService {
    String ID = ProgramService.class.getName();

    /**
     * adds a new program
     * @param program to add
     * @return 1 if successful
     */
    int addProgram(Program program);

    /**
     *  Updates a Program
     * @param program to update
     */
    void updateProgram(Program program);

    /**
     * deletes a Program
      * @param program to delete
     */
    void deleteProgram(Program program);

    /**
     * retrieves a program by id
     * @param id of the program to be retreived
     * @return a Program
     */
    Program getProgram(int id);

    /**
     * retieves a program by uid
     * @param uid of the program to be retrieved
     * @return
     */
    Program getProgram(String uid);

    /**
     * retrieves program by code
     * @param code of the program to be retrieved
     * @return
     */
    Program getProgramByCode(String code);

    /**
     *
     * @param name
     * @return
     */
    Program getProgramByName(String name);

    /**
     * retrieves all programs
      * @return a list of available programs
     */
    List<Program> getAllPrograms();

    List<Program> getProgramsBetween(int start, int end);

    List<Program> getProgramsBetweenByName(String name, int start, int end);

    int getProgramCount();

    int getProgramCountByName(String name);

}
