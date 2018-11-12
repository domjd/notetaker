package uk.dom.notetaker;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import uk.dom.notetaker.model.Note;
import uk.dom.notetaker.model.NoteRepository;
import uk.dom.notetaker.presenters.NoteViewModel;

import static junit.framework.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class NotePresenterTest {

    @Mock
    private NoteViewModel mNotePresenter;

    @Mock
    private NoteRepository mNoteRepository;

    private static List<Note> NOTES = new ArrayList<>();

    @Before
    public void setupTest(){
        MockitoAnnotations.initMocks(this);

        Application mockApplication = mock(Application.class);

        mNotePresenter = spy(new NoteViewModel(mockApplication));

    }

    @Test
    public void loadNotesTest(){
        verify(mNoteRepository.getAllNotes());
    }


}
