/**
 */
package Medical;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Hospital</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Medical.Hospital#getName <em>Name</em>}</li>
 *   <li>{@link Medical.Hospital#getDoctors <em>Doctors</em>}</li>
 * </ul>
 *
 * @see Medical.MedicalPackage#getHospital()
 * @model
 * @generated
 */
public interface Hospital extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see Medical.MedicalPackage#getHospital_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link Medical.Hospital#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Doctors</b></em>' containment reference list.
	 * The list contents are of type {@link Medical.Doctor}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Doctors</em>' containment reference list.
	 * @see Medical.MedicalPackage#getHospital_Doctors()
	 * @model containment="true"
	 * @generated
	 */
	EList<Doctor> getDoctors();

} // Hospital
