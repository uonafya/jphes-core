package org.hisp.dhis.jphes.program;

import com.google.api.client.util.Lists;
import org.hisp.dhis.dataelement.DataElement;
import org.hisp.dhis.dataelement.DataElementService;
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

    private ProgramElementStore programElementStore;

    public void setProgramElementStore(ProgramElementStore programElementStore) {
        this.programElementStore = programElementStore;
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

    @Override
    public List<ProgramElement> getProgramElements() {
        return null;
    }

    /**
     * Gets or adds a program data element for the given program and data element.
     *
     * @param programUid     the program identifier.
     * @param dataElementUid the data element identifier.
     * @return a program data element.
     */
    @Override
    public ProgramElement getOrAddProgramDataElement(String programUid, String dataElementUid) {
        Program program = programStore.getByUid( programUid );

        DataElement dataElement = dataElementService.getDataElement( dataElementUid );

        if ( program == null || dataElement == null )
        {
            return null;
        }

        ProgramElement programDataElement = programElementStore.get( program, dataElement );

        if ( programDataElement == null )
        {
            programDataElement = new ProgramElement( program, dataElement );

            programElementStore.save( programDataElement );
        }

        return programDataElement;
    }

    /**
     * Creates a program data element based on the program and data element with
     * the given program and data element identifiers.
     *
     * @param programUid     the program identifier.
     * @param dataElementUid the data element identifier.
     * @return a program data element.
     */
    @Override
    public ProgramElement getProgramDataElement(String programUid, String dataElementUid) {
        Program program = programStore.getByUid( programUid );

        DataElement dataElement = dataElementService.getDataElement( dataElementUid );

        if ( program == null || dataElement == null )
        {
            return null;
        }

        return new ProgramElement( program, dataElement );
    }

    /**
     * Returns a list of generated, non-persisted program data elements for the
     * program with the given identifier.
     *
     * @param programUid the program identifier.
     * @return a list of program data elements.
     */
    @Override
    public List<ProgramElement> getGeneratedProgramDataElements(String programUid) {
        Program program = getProgram( programUid );

        List<ProgramElement> programDataElements = Lists.newArrayList();

        if ( program == null )
        {
            return programDataElements;
        }

        for ( DataElement element : program.getDataElements() )
        {
            programDataElements.add( new ProgramElement( program, element ) );
        }

        Collections.sort( programDataElements );

        return programDataElements;
    }
}
