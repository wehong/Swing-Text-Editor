import javax.swing.*
import java.awt.*
import java.awt.event.*
import kotlin.system.exitProcess

class View : ActionListener, KeyListener {
    val jfMain:JFrame = JFrame("Swing Text Editor")
    val jtaEdit:JTextArea = JTextArea()
    val jmbMenuBar = JMenuBar()
    val jmFile = JMenu("파일")
    val jmEdit = JMenu("편집")
    val jmiFileNew = JMenuItem("새 파일")
    val jmiFileExit = JMenuItem("종료")
    val jmiEditCopy = JMenuItem("복사")
    val jmiEditPaste = JMenuItem("붙이기")

    var isModified = false

    init {
        jmFile.add(jmiFileNew)
        jmiFileNew.addActionListener(this)
        jmFile.add(jmiFileExit)
        jmiFileExit.addActionListener(this)
        jmEdit.add(jmiEditCopy)
        jmiEditCopy.addActionListener(this)
        jmEdit.add(jmiEditPaste)
        jmiEditPaste.addActionListener(this)
        jmbMenuBar.add(jmFile)
        jmbMenuBar.add(jmEdit)
        jfMain.jMenuBar = jmbMenuBar
        jfMain.contentPane.add(jtaEdit, BorderLayout.CENTER)
        jtaEdit.addKeyListener(this)

        jfMain.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }

    fun start() {
        jfMain.setSize(900, 600)
        jfMain.isVisible = true
    }

    override fun actionPerformed(ae:ActionEvent) {
        when (ae.source) {
            jmiFileNew -> {
                if (!isModified) {
                    jtaEdit.text = ""
                } else {
                    if (JOptionPane.showConfirmDialog(jfMain, "변경되었습니다. 그래도 새 파일을 여시겠습니까?", "새 파일", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
                        isModified = false
                        jtaEdit.text = ""
                    }
                }
            }
            jmiFileExit -> exitProcess(0)
            jmiEditCopy -> jtaEdit.copy()
            jmiEditPaste -> jtaEdit.paste()
        }
    }

    override fun keyTyped(ke:KeyEvent?) {
        isModified = true
    }
    override fun keyPressed(ke:KeyEvent) { }
    override fun keyReleased(ke:KeyEvent) { }
}

fun main(/*args: Array<String>*/) {
    val view = View()
    view.start()
}
